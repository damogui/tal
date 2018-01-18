package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum TMCState implements IEnum {

    WAIT_CONFIRM(0, "待确认"),//business_liense
    ADVICE(1, "异议"),//business_liense
    CONFIRMED(2, "已确认"),// trademark picture
    PAYED(3, "已付款"),// trademark picture
    //delegate proof
    OVER(4, "已完结");
    private int value;
    private String text;

    TMCState(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static TMCState getItem(int value) {

        for (TMCState item : values()) {

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
