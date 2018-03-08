package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * Created by zhangchao on 2018/3/7.
 */
public enum NOrderExchangeLogOperationType implements IEnum {

    AUTO(1, "自动"), MANUAL(2, "手动");

    private int value;
    private String text;

    NOrderExchangeLogOperationType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static NOrderExchangeLogOperationType getItem(int value) {

        for (NOrderExchangeLogOperationType item : values()) {

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
