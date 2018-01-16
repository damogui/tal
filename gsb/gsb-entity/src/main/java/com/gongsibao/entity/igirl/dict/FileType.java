package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum FileType implements IEnum {

    JPGB(0, "JPG黑"),//business_liense
    JPGC(1, "jpg彩"),//business_liense
    PNGB(2, "png黑"),// trademark picture
    PNGC(3, "png彩"),// trademark picture
    //delegate proof
    PDF(4, "pdf");
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
