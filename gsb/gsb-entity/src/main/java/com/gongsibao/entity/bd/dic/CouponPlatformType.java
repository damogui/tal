package com.gongsibao.entity.bd.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
public enum CouponPlatformType implements IEnum {
    PC(47101, "PC"),
    APP(47102, "APP"),
    WEIXIN(47103, "微信"),
    ICOMPANY(47104, "双子");

    private int value;
    private String text;

    CouponPlatformType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static CouponPlatformType getItem(int value) {
        for (CouponPlatformType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public Integer getValue() {

        return this.value;
    }
}
