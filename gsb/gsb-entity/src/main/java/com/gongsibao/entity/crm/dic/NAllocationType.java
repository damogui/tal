package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum NAllocationType implements IEnum{

	AUTO(1, "自动分配"), 
	MANUAL(2, "手动分配"),
	SemiAutomatic(3, "半自动分配");
	private int value;
	private String text;

	NAllocationType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static NAllocationType getItem(int value) {

		for (NAllocationType item : values()) {

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
