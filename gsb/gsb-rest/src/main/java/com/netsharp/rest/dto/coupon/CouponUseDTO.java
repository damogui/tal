package com.netsharp.rest.dto.coupon;

import com.netsharp.rest.dto.user.PreferentialCodeDTO;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: CouponOrderDTO
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 优惠券分为可用/不可用两种
 * @date 2018/4/20 11:16
 */
public class CouponUseDTO implements Serializable {
    private static final long serialVersionUID = 7447938651449258944L;

    /* 可用列表 */
    private List<PreferentialCodeDTO> available;
    /* 不可用列表 */
    private List<PreferentialCodeDTO> unavailable;

    public List<PreferentialCodeDTO> getAvailable() {
        return available;
    }

    public void setAvailable(List<PreferentialCodeDTO> available) {
        this.available = available;
    }

    public List<PreferentialCodeDTO> getUnavailable() {
        return unavailable;
    }

    public void setUnavailable(List<PreferentialCodeDTO> unavailable) {
        this.unavailable = unavailable;
    }
}
