package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderType implements IEnum  {

	Dd(1, "订单"), 
	Ht(2, "合同"), 
	Yybg(3, "企业并购");
	private int value;
	private String text;

	OrderType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderType getItem(int value) {

		for (OrderType item : values()) {

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