package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ServiceType implements IEnum {

	CUSTOMER_SERVICES(0, "客服"), AFTER_SALES(1, "业务");
	private int value;
	private String text;

	ServiceType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ServiceType getItem(int value) {

		for (ServiceType item : values()) {

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
