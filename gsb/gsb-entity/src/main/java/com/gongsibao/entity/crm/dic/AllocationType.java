package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AllocationType implements IEnum{

	NATURAL(1, "自然分配"), 
	ASSIGN(2, "指定部门");
	private int value;
	private String text;

	AllocationType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AllocationType getItem(int value) {

		for (AllocationType item : values()) {

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
