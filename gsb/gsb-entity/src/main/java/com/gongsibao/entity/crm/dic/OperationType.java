package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OperationType implements IEnum {

	AUTO(1, "自动"), MANUAL(2, "手动");

	private int value;
	private String text;

	OperationType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OperationType getItem(int value) {

		for (OperationType item : values()) {

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
