package com.gongsibao.rest.web.dto.coupon;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.bd.PreferentialCode;
import com.gongsibao.entity.bd.dic.CouponPlatformType;
import com.gongsibao.entity.trade.SoOrder;

import java.io.Serializable;
import java.util.Map;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 优惠券验证参数实体
 * @date $ $
 */
public class CouponValidateDTO implements Serializable {
    private static final long serialVersionUID = 2359362852732497843L;

    /* 优惠码 */
    private String couponNo;
    /* 订单 */
    private SoOrder order;
    /* 已有订单数量 */
    private int orderCount;
    /* 当前平台 */
    private CouponPlatformType platformType;
    /* 当前优惠券 */
    private PreferentialCode preferentialCode;

    /* dict type = 417 优惠券使用平台字典map */
    private Map<Integer, Dict> platformMap;

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public SoOrder getOrder() {
        return order;
    }

    public void setOrder(SoOrder order) {
        this.order = order;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public CouponPlatformType getPlatformType() {
        return platformType;
    }

    public void setPlatformType(CouponPlatformType platformType) {
        this.platformType = platformType;
    }

    public PreferentialCode getPreferentialCode() {
        return preferentialCode;
    }

    public void setPreferentialCode(PreferentialCode preferentialCode) {
        this.preferentialCode = preferentialCode;
    }

    public Map<Integer, Dict> getPlatformMap() {
        return platformMap;
    }

    public void setPlatformMap(Map<Integer, Dict> platformMap) {
        this.platformMap = platformMap;
    }
}
