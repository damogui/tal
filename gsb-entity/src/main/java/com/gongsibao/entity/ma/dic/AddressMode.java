package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 地址有效
 */
public enum AddressMode implements IEnum {

	VALID(1, "有效期内"), INVALID(2, "失效");
	private int value;
	private String text;

	AddressMode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AddressMode getItem(int value) {

		for (AddressMode item : values()) {

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