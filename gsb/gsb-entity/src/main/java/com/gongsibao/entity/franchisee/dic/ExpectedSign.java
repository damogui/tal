package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ExpectedSign  implements IEnum{

	EXPECTED_SIGN_1(1, "两天内"), 
	EXPECTED_SIGN_2(2, "一周内"),
	EXPECTED_SIGN_3(3, "一月内"),
	EXPECTED_SIGN_4(4, "三月内"),
	EXPECTED_SIGN_5(5, "三月以上");
	private int value;
	private String text;
	ExpectedSign(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ExpectedSign getItem(int value) {

		for (ExpectedSign item : values()) {

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
