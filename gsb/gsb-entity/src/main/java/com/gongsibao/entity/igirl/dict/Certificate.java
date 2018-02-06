package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 证件名称
 */
public enum Certificate implements IEnum {

    IDENTITY(0, "身份证"),
    PASSPORT(1, "护照"),
    OTHER(2, "其他");
    private int value;
    private String text;

    Certificate(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static Certificate getItem(int value) {

        for (Certificate item : values()) {

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
