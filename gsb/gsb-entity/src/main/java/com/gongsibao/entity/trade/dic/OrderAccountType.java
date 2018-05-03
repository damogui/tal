package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderAccountType implements IEnum  {

	wu(0, "默认"), 
	New(1, "新客户"), 
	Old(2, "老客户");
	private int value;
	private String text;

	OrderAccountType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderAccountType getItem(int value) {

		for (OrderAccountType item : values()) {

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
