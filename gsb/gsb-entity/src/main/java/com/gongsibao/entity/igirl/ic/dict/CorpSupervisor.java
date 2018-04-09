package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 监事类型
 * @author cyx
 *
 */
public enum CorpSupervisor implements IEnum {

	STOCKHOLDER(0, "股东监事"),
	STAFF(1, "职工监事");

	private int value;
	private String text;

	CorpSupervisor(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpSupervisor getItem(int value) {

		for (CorpSupervisor item : values()) {

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
