package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CapitalType  implements IEnum{

	CapitalType_1(1, "内资"), 
	CapitalType_2(2, "外资");
	private int value;
	private String text;
	CapitalType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CapitalType getItem(int value) {

		for (CapitalType item : values()) {

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
