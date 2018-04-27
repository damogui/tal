package com.gongsibao.entity.u8.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum VoucherLogType implements IEnum {

    Shoukuan(0, "收款"),
    Querenshouru(1, "确认收入"),
    Tuikuan(2, "退款"),
    Jiekuan(3, "借款"),
    Baoxiao(4, "报销"),
    Fukuan(5, "付款");


    private int value;
    private String text;

    VoucherLogType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static VoucherLogType getItem(int value) {

        for (VoucherLogType item : values()) {

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
