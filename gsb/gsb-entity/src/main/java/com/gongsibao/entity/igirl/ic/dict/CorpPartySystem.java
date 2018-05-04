package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 党组织建制
 * @author cyx
 *
 */
public enum CorpPartySystem implements IEnum {

	PARTYCOMMITTEE(0, "党委"),
	PARTYGENERALBRANCH(1, "党总支"),
	PARTYBRANCH(2, "党支部"),
	OTHER(3, "其他");


	private int value;
	private String text;

	CorpPartySystem(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpPartySystem getItem(int value) {

		for (CorpPartySystem item : values()) {

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
