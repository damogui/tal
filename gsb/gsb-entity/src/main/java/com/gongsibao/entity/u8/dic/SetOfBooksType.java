package com.gongsibao.entity.u8.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SetOfBooksType implements IEnum  {

	Zixun(0, "咨询"), 
	Keji(1, "科技");
	private int value;
	private String text;

	SetOfBooksType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SetOfBooksType getItem(int value) {

		for (SetOfBooksType item : values()) {

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
