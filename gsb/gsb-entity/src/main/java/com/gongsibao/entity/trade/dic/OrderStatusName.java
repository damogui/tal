package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//订单状态
public enum OrderStatusName implements IEnum  {	
	Ddfk(1, "等待付款"), 
	Yfqk(2, "已付全款"),
	Yfbfk(3, "已付部分款"),
	Blwc(4, "办理完成"),
	Sxdd(5, "失效订单");
	private int value;
	private String text;

	OrderStatusName(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderStatusName getItem(int value) {

		for (OrderStatusName item : values()) {

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
