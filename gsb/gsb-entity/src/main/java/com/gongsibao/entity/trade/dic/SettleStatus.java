package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SettleStatus implements IEnum {
    CONFIRM_SETTLEMENT(0, "待确认"),
    NO_SETTLEMENT(1, "待结算"),
    PORTION_SETTLEMENT(2, "已结算");
    //	FINISHED_SETTLEMENT(3, "部分结算");
    private int value;
    private String text;

    SettleStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static SettleStatus getItem(int value) {

        for (SettleStatus item : values()) {

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
