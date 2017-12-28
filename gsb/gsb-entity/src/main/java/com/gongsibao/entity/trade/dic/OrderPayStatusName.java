package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//订单支付成功记录
public enum OrderPayStatusName implements IEnum  {
	
	Dhk(3011, "待付款"), 
	Yfbfk(3012, "已付部分款"),
	Yfk(3013, "已付款");
	private int value;
	private String text;

	OrderPayStatusName(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderPayStatusName getItem(int value) {

		for (OrderPayStatusName item : values()) {

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