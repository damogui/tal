package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * Created by zhangchao on 2018/3/7.
 */
public enum NOrderExchangeLogType implements IEnum {
    Fenpei(1, "分配"),
    Zhuanyi(2, "转移");

    private int value;
    private String text;

    NOrderExchangeLogType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static NOrderExchangeLogType getItem(int value) {

        for (NOrderExchangeLogType item : values()) {

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