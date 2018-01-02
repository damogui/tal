package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//订单支付成功记录
public enum OrderPayStatusType implements IEnum  {
	
	Dhk(3011, "待付款"), 
	Yfbfk(3012, "已付部分款"),
	Yfk(3013, "已付款");
	private int value;
	private String text;

	OrderPayStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderPayStatusType getItem(int value) {

		for (OrderPayStatusType item : values()) {

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