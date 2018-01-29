package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CustomerFollowStatus implements IEnum {
	
	UNALLOCATION(1, "未分配"), 
	UNFollow(2, "待跟进"), 
	FOLLOWING(3, "跟进中"), 
	DEFEATED(4, "无法签单"), 
	SIGNED(5, "已签单"),
	UNSTART(6, "未启动");
	private int value;
	private String text;

	CustomerFollowStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CustomerFollowStatus getItem(int value) {

		for (CustomerFollowStatus item : values()) {

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