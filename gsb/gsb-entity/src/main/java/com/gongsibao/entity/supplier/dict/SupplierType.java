package com.gongsibao.entity.supplier.dict;

import org.netsharp.base.IEnum;

public enum SupplierType implements IEnum {

	SELFSUPPORT(1, "自营"), PLATFORM(2, "平台");
	private int value;
	private String text;

	SupplierType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public static SupplierType getItem(int value) {

		for (SupplierType item : values()) {

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
