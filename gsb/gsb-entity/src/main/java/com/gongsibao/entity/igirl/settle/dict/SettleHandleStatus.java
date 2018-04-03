package com.gongsibao.entity.igirl.settle.dict;

import org.netsharp.base.IEnum;

/**
 * 结算单处理状态
 */
public enum SettleHandleStatus implements IEnum {
    WU(0, "无"),
    PLATFORM_AUDITING(1, "平台审核中"),
    PLATFORM_REJECT(2, "平台驳回"),
    FINANCIAL_AUDITING(3, "财务审核中"),
    FINANCIAL_REJECT(4, "财务驳回"),
    UN_CASH(5, "未提现"),
    ALREADY_CASH(6, "已提现");

    private int value;
    private String text;

    SettleHandleStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
