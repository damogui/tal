package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 业务类型
 */
public enum BusinessType implements IEnum{
    ESTABLISHMENT(0,"设立"),
    CHANGE(1,"变更"),
    KEEP_ON_RECORD(2,"备案"),
    REGULATION(3,"增减补换证照"),
    EQUITY(4,"股权出质"),
    CANCELLATION(5,"注销");

    private Integer value;
    private String text;

    BusinessType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static BusinessType getItem(int value) {

        for (BusinessType item : values()) {

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
