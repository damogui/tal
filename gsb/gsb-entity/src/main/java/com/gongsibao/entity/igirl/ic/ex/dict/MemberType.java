package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum  MemberType implements IEnum{
    LEGAL_PERSON(0,"法定代表人"),
    EXECUTIVE_DIRECTOR(1,"执行董事"),
    SUPERVISOR(2,"监事"),
    MANAGER(3,"经理"),
    SECRETARY(4,"企业秘书联系人"),
    FINANCIAL_OFFICER(5,"财务负责人")
    ;
    private Integer value;
    private String text;

    MemberType(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static MemberType getItem(int value) {

        for (MemberType item : values()) {

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
