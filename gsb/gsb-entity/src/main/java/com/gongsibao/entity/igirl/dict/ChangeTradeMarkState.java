package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ChangeTradeMarkState implements IEnum {
    WAIT_CONFIRM(0, "待确认"),
    ADVICE(1, "异议"),
    RECEIVE_COMMIT(2, "已收提交"),
    OVER(3, "已完结");
    private int value;
    private String text;

    ChangeTradeMarkState(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static ChangeTradeMarkState getItem(int value) {

        for (ChangeTradeMarkState item : values()) {

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
