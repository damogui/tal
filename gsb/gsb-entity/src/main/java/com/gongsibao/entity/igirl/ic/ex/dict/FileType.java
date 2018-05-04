package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum FileType implements IEnum {
    DLWTS(0,"代理委托书"),
    SFZYJ(1,"身份证原件");


    private Integer value;
    private String text;

    FileType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static FileType getItem(int value) {

        for (FileType item : values()) {

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
