package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum MarkState implements IEnum {

    READY(0, "资料准备"),//business_liense
    DOCFINISH(1, "资料齐全"),//business_liense;
    WAITCOMMIT(2, "待提交"),//business_liense
    COMMITED(3, "人工已提交"),// trademark picture
    RECVCOMMIT(4, "已收提交"),// trademark picture
    RECVED(5, "已受理"),
    PARTREJECT(6, "部分驳回"),
    ALLREJECT(7, "全部驳回"),
    FIRSTPUB(8, "商家异议"),
    ROBOT(9, "机器人已提交"),
    PASSED(10, "已通过"),
	  FILLEXCEPTION(11, "填报异常");
    
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
