package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CooperativeMode implements IEnum{


	SP(1, "SP"), 
	CP(2, "CP"),
	TRAFFIC_PACKAGE(3, "流量包"),
	JOIN_IN(4, "加盟"),
	PARTNER(5, "合伙人");
	private int value;
	private String text;

	CooperativeMode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CooperativeMode getItem(int value) {

		for (CooperativeMode item : values()) {

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
