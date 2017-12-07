package com.gongsibao.entity.u8.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum U8BankType implements IEnum {

	Zhifu(0, "支付方式科目"), 
	Ywwu(1, "业务项科目");
	private int value;
	private String text;

	U8BankType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static U8BankType getItem(int value) {

		for (U8BankType item : values()) {

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
