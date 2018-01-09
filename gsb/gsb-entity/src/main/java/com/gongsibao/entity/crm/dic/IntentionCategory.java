package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum IntentionCategory implements IEnum {

	A(1, "A类"), 
	B(2, "B类"), 
	C(3, "C类"), 
	D(4, "D类");
	private int value;
	private String text;

	IntentionCategory(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static IntentionCategory getItem(int value) {

		for (IntentionCategory item : values()) {

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
