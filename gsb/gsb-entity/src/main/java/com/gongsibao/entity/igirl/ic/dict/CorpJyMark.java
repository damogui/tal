package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 报检标识
 * @author cyx
 *
 */
public enum CorpJyMark implements IEnum {

	SELF(0, "自理报检"),
	PROXY(1, "代理报检");

	private int value;
	private String text;

	CorpJyMark(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpJyMark getItem(int value) {

		for (CorpJyMark item : values()) {

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
