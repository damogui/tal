package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 性别
 * @author cyx
 *
 */
public enum CorpSex implements IEnum {

	MALE(1, "男"),
	FEMALE(2, "女");

	private int value;
	private String text;

	CorpSex(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpSex getItem(int value) {

		for (CorpSex item : values()) {

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
