package com.gongsibao.entity.igirl.dict;
import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 书式类型
 */
public enum WriteType implements IEnum {

	DALU(0, "中国大陆"), TAIWAN(1, "中国台湾"), HONGKONG(2, "中国香港");
	private int value;
	private String text;

	WriteType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static WriteType getItem(int value) {

		for (WriteType item : values()) {

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
