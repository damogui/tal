package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum FranchiseeAllotStatus implements IEnum {

	UNABSORBED(1, "未分配"), 
	ALLOCATED(2, "已分配");
	private int value;
	private String text;

	FranchiseeAllotStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static FranchiseeAllotStatus getItem(int value) {

		for (FranchiseeAllotStatus item : values()) {

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
