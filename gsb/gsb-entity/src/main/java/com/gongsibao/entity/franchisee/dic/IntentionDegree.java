package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum IntentionDegree implements IEnum{

	DEGREE_1(1, "高"), 
	DEGREE_2(2, "中"),
	DEGREE_3(3, "低");
	private int value;
	private String text;

	IntentionDegree(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static IntentionDegree getItem(int value) {

		for (IntentionDegree item : values()) {

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
