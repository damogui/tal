package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TransferType implements IEnum {
    TRANSFER(0,"商标转让"), DIVERT(1,"商标转移");
    private Integer value;
    private String text;

    TransferType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static TransferType getItem(Integer value) {

        for (TransferType item : values()) {

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
        return value;
    }
}
