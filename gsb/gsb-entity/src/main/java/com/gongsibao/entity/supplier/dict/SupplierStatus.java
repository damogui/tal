package com.gongsibao.entity.supplier.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum SupplierStatus implements IEnum{

	NOTOPEN(1, "未开户"), 
	OPEND(2, "已开户"),
	CLOSED(3, "已注销");//关闭时名下所有帐号都停用
	private int value;
	private String text;
	SupplierStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static SupplierStatus getItem(int value) {

		for (SupplierStatus item : values()) {

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
