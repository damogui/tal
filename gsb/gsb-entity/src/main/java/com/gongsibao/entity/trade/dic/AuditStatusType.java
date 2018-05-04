package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AuditStatusType implements IEnum {

    wu(0, "无"),
    Dsh(1051, "待审核"),
    Shz(1052, "审核中"),
    Bhsh(1053, "驳回审核"),
    Shtg(1054, "审核通过"),
    Shpd(1055, "审核排队"),
    Shgb(1056, "审核关闭");
    private int value;
    private String text;

    AuditStatusType(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static AuditStatusType getItem(int value) {

        for (AuditStatusType item : values()) {

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
