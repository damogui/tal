package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum TradeMarkType implements IEnum {

	GENERAL(0,"tmType1", "一般"), SHARE(1,"tmType2", "集体"),PROOF(2,"tmType3","证明");
	//TODO(?)
	private Integer value;
	private String text;
	private String content;

	TradeMarkType(Integer value,String content, String text) {
		this.value = value;
		this.content = content;
		this.text = text;
	}

	@JsonCreator
	public static TradeMarkType getItem(Integer value) {

		for (TradeMarkType item : values()) {

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
