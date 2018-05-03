package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 工商注册核准状态
 */
public enum ApprovalType implements IEnum{
    WAIT(0,"待审核"),
    WAITED(1,"已审核");


    private Integer value;
    private String text;

    ApprovalType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static ApprovalType getItem(int value) {

        for (ApprovalType item : values()) {

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
