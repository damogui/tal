package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum RefundWayType implements IEnum  {
	
	Dsh(3131, "线上"), 
	Tkz(3132, "线下");
	private int value;
	private String text;
	RefundWayType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static RefundWayType getItem(int value) {

		for (RefundWayType item : values()) {

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
