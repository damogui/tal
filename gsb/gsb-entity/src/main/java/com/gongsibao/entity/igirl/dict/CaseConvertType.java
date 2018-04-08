package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CaseConvertType implements IEnum {

    ERROR_0(0, "方案不存在"),
    ERROR_1(-1, "方案选项不存在"),
    ERROR_2(-2, "方案未关联产品"),
    ERROR_3(-3, "客户手机号错误"),
    ERROR_4(-4, "方案未填写公司"),
    ERROR_5(-5, "订单不存在"),
    ERROR_6(-6, "订单未付款"),
    ERROR_7(-7, "方案已绑定其他订单"),
    ERROR_8(-8, "方案与订单价格不符"),
    ERROR_9(-9, "方案与订单产品数量不符"),
    ERROR_10(-10, "方案与订单产品不符"),
    ERROR_11(-11, "订单已经关联其他方案"),
    ERROR_12(-12, "该订单已付款，不允许重新生成"),
    SUCCESS(1, "成功");

    private int value;
    private String text;

    CaseConvertType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static CaseConvertType getItem(int value) {

        for (CaseConvertType item : values()) {

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
