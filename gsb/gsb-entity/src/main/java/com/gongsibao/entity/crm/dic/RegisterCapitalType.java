package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum RegisterCapitalType implements IEnum{

	CompanyType_0(0, "人民币(万)"), 
	CompanyType_1(1, "美元(万)");
	private int value;
	private String text;
	RegisterCapitalType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static RegisterCapitalType getItem(int value) {

		for (RegisterCapitalType item : values()) {

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
