package com.gongsibao.bd.service.auditLog;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AuditState implements IEnum {

    NOTPASS(0, "审核驳回"), PASS(1, "审核通过");
    private int value;
    private String text;

    AuditState(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static AuditState getItem(int value) {

        for (AuditState item : values()) {

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
