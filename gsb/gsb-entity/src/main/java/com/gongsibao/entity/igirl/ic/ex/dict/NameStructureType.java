package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 名称结构
 */
public enum NameStructureType implements IEnum {
    ONE(0,"地域+字号+行业特点"),
    TWO(1,"字号+地域+行业特点"),
    THREE(2,"字号+行业特点+地域");


    private Integer value;
    private String text;

    NameStructureType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static NameStructureType getItem(int value) {

        for (NameStructureType item : values()) {

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
