package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ApplierType implements IEnum {

	PUBLIC(0, "法人或其它组织"), PRIVATE(1, "自然人");
	private int value;
	private String text;

	ApplierType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ApplierType getItem(int value) {

		for (ApplierType item : values()) {

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
