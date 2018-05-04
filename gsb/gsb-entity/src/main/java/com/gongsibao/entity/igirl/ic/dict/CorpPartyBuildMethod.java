package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 党组织组建方式
 * @author cyx
 *
 */
public enum CorpPartyBuildMethod implements IEnum {

	INDEPENDENT(0, "单独组建"),
	UNION(1, "联合组建"),
	AffILIATED(2, "挂靠"),
	OTHER(3, "其他");


	private int value;
	private String text;

	CorpPartyBuildMethod(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpPartyBuildMethod getItem(int value) {

		for (CorpPartyBuildMethod item : values()) {

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
