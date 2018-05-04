package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 住所提供方式
 * @author cyx
 *
 */
public enum CorpResidenceProvision implements IEnum {

	OWN(0, "自有"),
	LEASE(1, "租赁"),
	FREE(1, "无偿使用"),
	OTHER(1, "其他");
	private int value;
	private String text;

	CorpResidenceProvision(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpResidenceProvision getItem(int value) {

		for (CorpResidenceProvision item : values()) {

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
