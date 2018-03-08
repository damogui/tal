package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PayForOrderCountType implements IEnum  {

	Ybdd(0, "一笔一单"), 
	Ybduodan(1, "一笔多单");
	private int value;
	private String text;

	PayForOrderCountType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PayForOrderCountType getItem(int value) {

		for (PayForOrderCountType item : values()) {

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
