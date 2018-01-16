package com.gongsibao.entity.igirl.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TradeMarkType implements IEnum {

	GENERAL(0, "一般"), SHARE(1, "集体"),PROOF(2,"证明");
	private int value;
	private String text;

	TradeMarkType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TradeMarkType getItem(int value) {

		for (TradeMarkType item : values()) {

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
