package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 真假
 * @author cyx
 *
 */
public enum CorpBoolean implements IEnum {

	F(0, "否"),
	T(1, "是");

	private int value;
	private String text;

	CorpBoolean(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpBoolean getItem(int value) {

		for (CorpBoolean item : values()) {

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
