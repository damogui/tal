package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 变更类型
 */
public enum ChangeType implements IEnum {
    CHANGE_M(0, "变更名义","radio_m"),CHANGE_D(1,"变更地址","radio_d"),CHANGE_MD(2,"变更名义/地址","radio_md");
    private int value;
    private String text;
    private String content;

    ChangeType(int value, String text,String content) {
        this.value = value;
        this.text = text;
        this.content = content;
    }

    @JsonCreator
    public static ChangeType getItem(int value) {

        for (ChangeType item : values()) {

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

    public String getContent() {
        return content;
    }
}
