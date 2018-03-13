package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum RefundType implements IEnum {
	
	PORTION(0, "部分退款"), 
	FULL(1, "全额退款");
	private int value;
	private String text;
	RefundType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static RefundType getItem(int value) {

		for (RefundType item : values()) {

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
