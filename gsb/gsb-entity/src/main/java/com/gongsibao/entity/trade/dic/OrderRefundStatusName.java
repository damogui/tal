package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//退单状态
public enum OrderRefundStatusName implements IEnum  {
	
	Dsh(3031, "待审核"), 
	Tkz(3032, "退款中"),
	Tkwc(3033, "退款完成"),
	Bhtk(3034, "驳回退款");
	private int value;
	private String text;

	OrderRefundStatusName(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderRefundStatusName getItem(int value) {

		for (OrderRefundStatusName item : values()) {

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
