package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BusinessScope implements IEnum{

	Scope_1(1, "工商服务"),
	Scope_2(2, "知识产权"),
	Scope_3(3, "财会税务");
	private int value;
	private String text;

	BusinessScope(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BusinessScope getItem(int value) {

		for (BusinessScope item : values()) {

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
