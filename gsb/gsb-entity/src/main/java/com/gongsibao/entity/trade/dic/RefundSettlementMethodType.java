package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * Created by zhangchao on 2018/3/19.
 */
public enum RefundSettlementMethodType implements IEnum {

    //01和07对应财务现金流编号
    wu(1, "无"),
    Yjs(2, "已结算"),//对应财务现金流：01
    Wjs(3, "未结算");//对应财务现金流：07
    private int value;
    private String text;

    RefundSettlementMethodType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static RefundSettlementMethodType getItem(int value) {

        for (RefundSettlementMethodType item : values()) {

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