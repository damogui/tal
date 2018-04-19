package com.gongsibao.rest.service.bd.impl;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.bd.base.IPreferentialCodeService;
import com.gongsibao.bd.base.IPreferentialDataMapService;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.Preferential;
import com.gongsibao.entity.bd.PreferentialCode;
import com.gongsibao.entity.bd.PreferentialDataMap;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.dict.ResponseStatus;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.common.util.AmountUtils;
import com.gongsibao.rest.common.util.StringUtils;
import com.gongsibao.rest.dto.coupon.CouponValidateDTO;
import com.gongsibao.rest.service.bd.ICouponService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
@Service
public class CouponService implements ICouponService {

    // 优惠券码服务
    IPreferentialCodeService basePreferentialCodeService = ServiceFactory.create(IPreferentialCodeService.class);

    // 优惠券关联关系服务
    IPreferentialDataMapService preferentialDataMapService = ServiceFactory.create(IPreferentialDataMapService.class);

    @Override
    public Map<String, PreferentialCode> mapByNos(Collection<String> noList) {
        Map<String, PreferentialCode> result = new HashMap<>();

        // 查优惠码实体
        List<PreferentialCode> preferentialCodes = basePreferentialCodeService.byNos(noList);
        if (CollectionUtils.isEmpty(preferentialCodes)) {
            return null;
        }

        List<Integer> preferentialIds = new ArrayList<>();
        for (PreferentialCode preferentialCode : preferentialCodes) {
            preferentialIds.add(preferentialCode.getPreferential().getId());
        }

        // 查优惠券区域、产品、套餐
        Map<Integer, List<PreferentialDataMap>> dataMap = preferentialDataMapService.mapByPreferentialIds(preferentialIds);

        // 封装return
        for (PreferentialCode preferentialCode : preferentialCodes) {
            result.put(preferentialCode.getNo(), preferentialCode);

            Preferential preferential = preferentialCode.getPreferential();
            if (null == preferential) {
                continue;
            }

            List<PreferentialDataMap> dataMapList = dataMap.get(preferential.getId());
            if (CollectionUtils.isEmpty(dataMapList)) {
                continue;
            }

            for (PreferentialDataMap preferentialDataMap : dataMapList) {
                if (preferentialDataMap.getType() == 1) {
                    preferential.getProdIdList().add(preferentialDataMap.getDataId());
                } else if (preferentialDataMap.getType() == 2) {
                    preferential.getPackageIdList().add(preferentialDataMap.getDataId());
                } else {
                    preferential.getCityIdList().add(preferentialDataMap.getDataId());
                }
            }
        }
        return result;
    }

    @Override
    public Result validOrderCoupon(CouponValidateDTO dto) {
        // 订单
        SoOrder order = dto.getOrder();
        // 优惠券信息
        PreferentialCode preferentialCode = dto.getPreferentialCode();
        Preferential coupon = preferentialCode.getPreferential();
        // 当前平台
        CouponPlatformType platformType = dto.getPlatformType();
        // 当前用户下单次数
        int orderCount = dto.getOrderCount();
        // 优惠券平台字典信息
        Map<Integer, Dict> platformMap = dto.getPlatformMap();

        Date now = new Date();
        if (null == coupon) {
            return new Result<>(ResponseStatus.FAILED, "优惠券不存在");
        }

        if (preferentialCode.getStatus() != 1) {
            return new Result<>(ResponseStatus.FAILED, "优惠券" + (preferentialCode.getStatus() == 0 ? "未激活" : "已使用"));
        }

        if (null == preferentialCode.getAccountId() || preferentialCode.getAccountId().compareTo(order.getAccountId()) != 0) {
            return new Result<>(ResponseStatus.FAILED, "优惠券不不属于您");
        }

        if (coupon.getIsEnabled() == 0) {
            return new Result<>(ResponseStatus.FAILED, "优惠券已停用");
        }

        if (coupon.getStartDate().after(now) || coupon.getEndDate().before(now)) {
            return new Result<>(ResponseStatus.FAILED, "该优惠券未在使用期限");
        }

        if (null != order && order.getPayablePrice() < coupon.getAmountLimit()) {
            return new Result<>(ResponseStatus.FAILED, "该优惠券小于可用金额限制");
        }

        if (coupon.getIsFirstOrderUse() == 0 && orderCount > 0) {
            return new Result<>(ResponseStatus.FAILED, "该优惠券只能首单可用");
        }

        // 优惠券使用平台判断
        if (null != platformType) {
            List<Integer> platformIds = StringUtils.idsToList(coupon.getUsePlatform());
            if (CollectionUtils.isNotEmpty(platformIds)) {
                if (!platformIds.contains(platformType.getValue())) {
                    List<String> platformNames = new ArrayList<>();
                    for (Integer platformId : platformIds) {
                        Dict dict = platformMap.get(platformId);
                        if (null == dict) {
                            continue;
                        }
                        platformNames.add(dict.getName());
                    }
                    return new Result<>(ResponseStatus.FAILED, "该优惠券[" + dto.getCouponNo() + "]仅限在" + StringUtils.join(platformNames, "、") + "等平台使用");
                }
            }
        }

        if (null != order) {
            List<Integer> allProdIds = new ArrayList<>();
            List<Integer> allCityIds = new ArrayList<>();
            for (OrderProd orderProd : order.getProducts()) {
                allProdIds.add(orderProd.getProductId());
                allCityIds.add(orderProd.getCityId());
            }

            // 套餐 || 单品判断
            Integer packageId = order.getPackageId();
            if (packageId > 0) {
                List<Integer> packageIdList = coupon.getPackageIdList();
                if (CollectionUtils.isNotEmpty(packageIdList) && !packageIdList.contains(packageId)) {
                    return new Result<>(ResponseStatus.FAILED, "该优惠券当前套餐不可用");
                }
            } else {

                List<Integer> prodIdList = coupon.getProdIdList();
                if (CollectionUtils.isNotEmpty(prodIdList)) {
                    if (!prodIdList.containsAll(allProdIds)) {
                        return new Result<>(ResponseStatus.FAILED, "该优惠券当前商品不可用");
                    }
                }
            }

            List<Integer> cityIdList = coupon.getCityIdList();
            if (CollectionUtils.isNotEmpty(cityIdList)) {
                if (!cityIdList.containsAll(allCityIds)) {
                    return new Result<>(ResponseStatus.FAILED, "该优惠券当前城市不可用");
                }
            }
        }
        return new Result<>(ResponseStatus.SUCCESS, "可用");
    }

    @Override
    public Double couponPrice(Double price, Preferential coupon) {
        if (null == coupon) {
            return 0d;
        }

        Integer category = coupon.getCategory();
        if (category == 0) {
            // 代金券
            return Double.valueOf(coupon.getPreferentialAmount());
        } else {
            // 折扣率
            double discount = AmountUtils.sub(1, AmountUtils.div(coupon.getDiscount(), 10, 2));
            return AmountUtils.div(AmountUtils.sub(price, discount), 1, 2);
        }
    }

    @Override
    public boolean updateCodeStatus(Collection<String> noList, Integer status, Integer orderId) {


        return false;
    }
}
