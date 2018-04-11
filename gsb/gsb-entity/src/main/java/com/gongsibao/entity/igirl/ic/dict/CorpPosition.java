package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 职务
 * @author cyx
 *
 */
public enum CorpPosition implements IEnum {


	EXECUTIVE(0, "执行董事"),
	CHAIRMAN(1, "董事长"),
	DIRECTOR(2, "董事");

	private int value;
	private String text;

	CorpPosition(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpPosition getItem(int value) {

		for (CorpPosition item : values()) {

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
