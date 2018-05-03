package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 任职期限
 * @author cyx
 *
 */
public enum CorpDeadline implements IEnum {

	ONE(0, "1年"),
	TWO(1, "2年"),
	THREE(2, "3年");

	private int value;
	private String text;

	CorpDeadline(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpDeadline getItem(int value) {

		for (CorpDeadline item : values()) {

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
