package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 报税模式
 */
public enum BaoshuiMode implements IEnum{

	VALID(1, "正常"), INVALID(2, "非正常户");
	private int value;
	private String text;

	BaoshuiMode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BaoshuiMode getItem(int value) {

		for (BaoshuiMode item : values()) {

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