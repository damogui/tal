package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum IcFileType implements IEnum {
    PROOF_MATERIAL(0,"证明材料"),
    MEMBER_FILE(1,"主要人员材料"),
    CUSTOM_STATUTE(2,"自定义章程");

    private int value;
    private String text;

    IcFileType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static IcFileType getItem(int value) {

        for (IcFileType item : values()) {

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
