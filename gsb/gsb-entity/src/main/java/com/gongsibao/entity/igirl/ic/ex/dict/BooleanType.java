package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BooleanType implements IEnum{
    YES(0,"是"),NO(1,"否");

    private Integer value;
    private String text;

    BooleanType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static BooleanType getItem(int value) {

        for (BooleanType item : values()) {

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
