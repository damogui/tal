package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 产生方式
 * @author cyx
 *
 */
public enum CorpBuildMothed implements IEnum {

	VOTE(0, "选举（适用于普通有限公司）"),
	APPOINT(1, "委派（适用于国有独资）");


	private int value;
	private String text;

	CorpBuildMothed(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpBuildMothed getItem(int value) {

		for (CorpBuildMothed item : values()) {

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
