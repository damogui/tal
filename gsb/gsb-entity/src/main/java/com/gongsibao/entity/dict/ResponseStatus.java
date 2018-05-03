package com.gongsibao.entity.dict;

import org.netsharp.base.IEnum;

public enum ResponseStatus implements IEnum {
    SUCCESS(1, "成功"),
    FAILED(-1, "失败");

    private Integer value;
    private String text;

    ResponseStatus(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
