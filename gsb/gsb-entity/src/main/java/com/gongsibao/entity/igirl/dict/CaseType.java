package com.gongsibao.entity.igirl.dict;

import com.gongsibao.entity.trade.dic.OrderType;
import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum  CaseType implements IEnum {
    TRADEMARK_REG(1, "商标注册方案");

    private int value;
    private String text;

    CaseType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return null;
    }

    @Override
    public Integer getValue() {
        return null;
    }

    @JsonCreator
    public static CaseType getItem(int value) {
        for (CaseType item : values()) {
            if (item.getValue() == value) {
                return item;
            }
        }
        return null;
    }

}
