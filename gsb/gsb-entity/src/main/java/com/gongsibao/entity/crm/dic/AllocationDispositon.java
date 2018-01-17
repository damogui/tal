package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AllocationDispositon implements IEnum{
	
	DIRECT(1, "自营"), 
	PLATFORM(2, "平台");
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
