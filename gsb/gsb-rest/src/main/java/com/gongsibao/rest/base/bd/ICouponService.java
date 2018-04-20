package com.gongsibao.rest.base.bd;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.bd.Preferential;
import com.gongsibao.entity.bd.PreferentialCode;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.dto.coupon.CouponUseDTO;
import com.gongsibao.rest.dto.coupon.CouponValidateDTO;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName: IPreferentialService
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO 优惠券
 * @date $ $
 */
public interface ICouponService {

    /**
     * @param
     * @return
     * @Description: 通过优惠码，查询优惠券
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/18
     */
    Map<String, PreferentialCode> mapByNos(Collection<String> noList);

    /**
     * @param dto 优惠券验证所需实体
     * @return
     * @Description: 验证优惠券是否可用
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/18
     */
    Result validOrderCoupon(CouponValidateDTO dto);

    /**
     * @Description:计算优惠券价格
     * @param
     * @return 注意，此处返回优惠价格，不是计算后的价格
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/18
     */
    Double couponPrice(Double price, Preferential coupon);

    /* *
     * @Description:TODO
     * @param  [accountId, platformType, order]
     * @return com.gongsibao.entity.Result<java.util.Map<java.lang.String,java.lang.Object>> 
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/20
     */
    CouponUseDTO findAccountCoupons(Integer accountId, CouponPlatformType platformType, SoOrder order);
}
