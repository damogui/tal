package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderStageNum implements IEnum{

	ONE(1, "不分期"), 	
	TWO(2, "二期"),
	THREE(3, "三期"),
	FOUR(4, "四期");
	
	private int value;
	private String text;

	OrderStageNum(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderStageNum getItem(int value) {

		for (OrderStageNum item : values()) {

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
