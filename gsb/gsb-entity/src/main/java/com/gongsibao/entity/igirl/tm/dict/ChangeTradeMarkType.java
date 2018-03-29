package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ChangeTradeMarkType implements IEnum {

	GENERAL(0,"radio_p", "普通商标"), SHARE(1,"radio_z", "证明商标"),PROOF(2,"radio_j","集体商标");
	private Integer value;
	private String text;
	private String content;

	ChangeTradeMarkType(Integer value, String content, String text) {
		this.value = value;
		this.content = content;
		this.text = text;
	}

	@JsonCreator
	public static ChangeTradeMarkType getItem(Integer value) {

		for (ChangeTradeMarkType item : values()) {

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
		return value;
	}

	public String getContent() {
		return content;
	}
}
