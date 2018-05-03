package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 住所产权类型
 * @author cyx
 *
 */
public enum CorpPropertyRight implements IEnum {

	NOHAVE(0, "无房产证"),
	HAVE(1, "有房产证");
	private int value;
	private String text;

	CorpPropertyRight(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpPropertyRight getItem(int value) {

		for (CorpPropertyRight item : values()) {

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
