package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum  IGirlServiceType implements IEnum{
    TRADEMARK_APPLY(0,"商标申请"),
    TRADEMARK_CHANGE(1,"商标变更"),
    TRADEMARK_TRANSFER(2,"商标转让"),
    INDUSTRIAL_COMMERCIAL_APPLY(4,"工商注册");


    private Integer value;
    private String text;

    IGirlServiceType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static IGirlServiceType getItem(int value) {

        for (IGirlServiceType item : values()) {

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
