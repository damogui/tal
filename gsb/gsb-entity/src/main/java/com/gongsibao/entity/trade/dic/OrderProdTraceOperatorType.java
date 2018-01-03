package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderProdTraceOperatorType implements IEnum {

	wu(0, "无"), 
	Ztth(315101, "状态回退"), 
	Czrbz(315201, "操作人备注"), 
	Jkrbz(315202, "监控人备注"), 
	Clshtg(315301, "材料审核通过"), 
	Clshbtg(315302, "材料审核不通过");
	
	private int value;
	private String text;

	OrderProdTraceOperatorType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderProdTraceOperatorType getItem(int value) {

		for (OrderProdTraceOperatorType item : values()) {

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