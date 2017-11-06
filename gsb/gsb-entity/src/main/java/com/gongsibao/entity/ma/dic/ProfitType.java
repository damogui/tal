package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ProfitType implements IEnum {


	UNLIMITED(1, "不限"), YES(2, "是"),NO(3, "否");
	private int value;
	private String text;

	ProfitType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ProfitType getItem(int value) {

		for (ProfitType item : values()) {

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
