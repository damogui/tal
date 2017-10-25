package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum Sex implements IEnum {

	SECRECY(0, "保密"), MAN(1, "男"), WOMAN(2, "女");
	private int value;
	private String text;

	Sex(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static Sex getItem(int value) {

		for (Sex item : values()) {

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
