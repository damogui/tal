package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum OrderStatus  implements IEnum{


	Submit(0, "提交"), Aduited(1, "已审核"), Stop(2, "中止");
	private int value;
	private String text;

	OrderStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static OrderStatus getItem(int value) {

		for (OrderStatus item : values()) {

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
