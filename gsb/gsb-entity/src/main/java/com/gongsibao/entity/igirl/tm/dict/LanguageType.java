package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 语言类型
 */
public enum  LanguageType implements IEnum{
    CHINESE(0, "中文"),FOREIGN(1, "外文");
    private int value;
    private String text;

    LanguageType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static LanguageType getItem(int value) {

        for (LanguageType item : values()) {

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
