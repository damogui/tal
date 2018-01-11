package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum FileType implements IEnum {

    JPG(0, "jpg"),//business_liense
    PNG(1, "png"),// trademark picture
    //delegate proof
    PDF(2, "pdf");
    private int value;
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
