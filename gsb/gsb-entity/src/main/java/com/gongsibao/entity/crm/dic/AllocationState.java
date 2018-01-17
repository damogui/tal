package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AllocationState implements IEnum{

	ALLOCATED(1, "已分配"), 
	WAIT(2, "待分配");
	private int value;
	private String text;

	AllocationState(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AllocationState getItem(int value) {

		for (AllocationState item : values()) {

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
