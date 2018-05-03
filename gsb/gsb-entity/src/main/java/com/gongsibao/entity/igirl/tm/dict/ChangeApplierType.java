package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ChangeApplierType implements IEnum {

	PUBLIC(0, "法人或其他组织"), PRIVATE(1, "自然人");
	private int value;
	private String text;

	ChangeApplierType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ChangeApplierType getItem(int value) {

		for (ChangeApplierType item : values()) {

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
