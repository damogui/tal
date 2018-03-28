package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum InfoType implements IEnum {

    JPGB(0, "新闻"),//business_liense
    JPGC(1, "新品");//business_liense
    private int value;
    private String text;

    InfoType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static InfoType getItem(int value) {

        for (InfoType item : values()) {

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
