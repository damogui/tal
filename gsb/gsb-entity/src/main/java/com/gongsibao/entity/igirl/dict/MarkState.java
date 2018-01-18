package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum MarkState implements IEnum {

    READY(0, "资料准备"),//business_liense
    WAITCOMMIT(1, "待提交"),//business_liense
    COMMITED(2, "已提交"),// trademark picture
    RECVCOMMIT(3, "已收提交"),// trademark picture
    RECVED(4, "已受理"),
    PARTREJECT(5, "部分驳回"),
    ALLREJECT(6, "全部驳回"),
    FIRSTPUB(7, "商家异议"),
    PASSED(8, "已通过");
    private int value;
    private String text;

    MarkState(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @JsonCreator
    public static MarkState getItem(int value) {

        for (MarkState item : values()) {

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
