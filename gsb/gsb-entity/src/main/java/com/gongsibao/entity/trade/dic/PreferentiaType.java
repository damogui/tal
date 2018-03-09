package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PreferentiaType  implements IEnum{

	BACKGROUND(3091, "后台优惠"), 
	COUPON(3092, "优惠券");
	private int value;
	private String text;

	PreferentiaType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PreferentiaType getItem(int value) {

		for (PreferentiaType item : values()) {

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
