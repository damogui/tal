package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 证件名称
 */
public enum CertificateType implements IEnum {

    IDENTITY(0, "身份证"),
    PASSPORT(1, "护照"),
    OTHER(2, "其他");
    private int value;
    private String text;

    CertificateType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static CertificateType getItem(int value) {

        for (CertificateType item : values()) {

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
