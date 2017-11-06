package com.gongsibao.entity.taurus.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PaymentType implements IEnum {

	UNKNOWN(0, "未知"),WEIXIN(1, "微信支付"), ALIPAY(2, "支付宝"), CASH(3, "现金"), TRANSFER(4, "转账");
	private int value;
	private String text;

	PaymentType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PaymentType getItem(int value) {

		for (PaymentType item : values()) {

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
