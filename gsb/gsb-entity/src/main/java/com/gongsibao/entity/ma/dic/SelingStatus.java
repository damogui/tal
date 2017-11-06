package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SelingStatus implements IEnum {

	UNSOLD(1, "未出售"), RESERVE(2, "预订"), BESOLD(3, "已出售");
	private int value;
	private String text;

	SelingStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SelingStatus getItem(int value) {

		for (SelingStatus item : values()) {

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
