package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum NodeType implements IEnum {

    Kaishi(2061, "开始"),
    Jiesu(2062, "结束"),
    Zanding(2063, "暂停"),
    Jiesuan(2064, "结算");
    private int value;
    private String text;

    NodeType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static NodeType getItem(int value) {

        for (NodeType item : values()) {

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