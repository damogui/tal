package com.gongsibao.entity.igirl.ic.ex.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 工商注册核准状态
 */
public enum ApprovalType implements IEnum{
    UNSUBMIT(0,"未提交"),
    WIATAUDIT(1,"待审核"),
    APPROVED(2,"已核准"),
    REVERSION(3,"退回修改"),
    INADMISSIBLE(4,"不予受理"),
    PRE_NUCLEAR_PASS(5,"预核通过"),
    PRE_NUCLEAR_NOT_PASS(6,"预核不通过");

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
