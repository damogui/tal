package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum FranchiseeTrackType implements IEnum{

	MANUAL(1, "手动"), 
	SYSTEM(2, "系统");
	private int value;
	private String text;
	FranchiseeTrackType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static FranchiseeTrackType getItem(int value) {

		for (FranchiseeTrackType item : values()) {

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
