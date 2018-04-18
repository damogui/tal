package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdOperationAllocationStatus implements IEnum {

    Wfp(0, "未分配"),
    Yfp(1, "已分配");
    private int value;
    private String text;

    OrderProdOperationAllocationStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static OrderProdOperationAllocationStatus getItem(int value) {

        for (OrderProdOperationAllocationStatus item : values()) {

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