package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AllocationDispositon implements IEnum{
	
	UNLIMITED(1, "不限"), 
	DIRECT(2, "自营"), 
	PLATFORM(3, "平台");
	private int value;
	private String text;

	AllocationDispositon(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AllocationDispositon getItem(int value) {

		for (AllocationDispositon item : values()) {

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
