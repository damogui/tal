package com.gongsibao.entity.taurus.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AttentionStatus implements IEnum {
	
	ATTENTION(0, "未标记"), UNATTENTION(1, "已标记");
	private int value;
	private String text;

	AttentionStatus(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AttentionStatus getItem(int value) {

		for (AttentionStatus item : values()) {

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
