package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 *住所是否在以下区域
 * @author cyx
 *
 */
public enum CorpSpecialZone  implements IEnum {

	NONE(0, "不在以下区域"),
	TIANANMEN(1, "天安门地区"),
	XIKEZHAN(2, "西客站地区");


	private int value;
	private String text;

	CorpSpecialZone(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpSpecialZone getItem(int value) {

		for (CorpSpecialZone item : values()) {

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
