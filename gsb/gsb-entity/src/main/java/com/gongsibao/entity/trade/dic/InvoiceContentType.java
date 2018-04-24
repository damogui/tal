package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum InvoiceContentType implements IEnum {
    wu(0, "无"),
    Fwf(1, "服务费"),
    Zxf(2, "咨询费");
    private int value;
    private String text;

    InvoiceContentType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static InvoiceContentType getItem(int value) {

        for (InvoiceContentType item : values()) {

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