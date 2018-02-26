package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 变更证明文件是否为中文
 */
public enum ProveLanguageType implements IEnum{
    YES(0, "是","ifBgzmEn1"),NO(1, "否","ifBgzmEn");
    private int value;
    private String text;
    private String content;

    ProveLanguageType(int value, String text,String content) {
        this.value = value;
        this.text = text;
        this.content = content;
    }

    @JsonCreator
    public static ProveLanguageType getItem(int value) {

        for (ProveLanguageType item : values()) {

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
