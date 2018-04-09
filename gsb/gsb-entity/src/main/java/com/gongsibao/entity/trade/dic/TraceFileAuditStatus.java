package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TraceFileAuditStatus implements IEnum{

	TOAUDIT(1051, "待审核"),
	REJECTED(1053, "驳回审核"),
	PASS(1054, "审核通过");
	
    private int value;
    private String text;

    TraceFileAuditStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static TraceFileAuditStatus getItem(int value) {

        for (TraceFileAuditStatus item : values()) {

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
