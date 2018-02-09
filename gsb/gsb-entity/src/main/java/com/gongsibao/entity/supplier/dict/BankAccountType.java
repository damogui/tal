package com.gongsibao.entity.supplier.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BankAccountType implements IEnum{

	ENTERPRISE(1, "单位帐户"), 
	PERSON(2, "个人帐户");
	private int value;
	private String text;
	BankAccountType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BankAccountType getItem(int value) {

		for (BankAccountType item : values()) {

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

