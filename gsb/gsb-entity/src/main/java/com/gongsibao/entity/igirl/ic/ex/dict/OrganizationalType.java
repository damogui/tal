package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrganizationalType implements IEnum{
    LIMITED_COMPANY(0,"有限公司"),
    LIMITED_LIABILITY_COMPANY(1,"有限责任公司"),
    STOCK_COMPANY(2,"股份公司"),
    STOCK_LIMITED_COMPANY(3,"股份有限公司");

    private Integer value;
    private String text;

    OrganizationalType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static OrganizationalType getItem(int value) {

        for (OrganizationalType item : values()) {

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
