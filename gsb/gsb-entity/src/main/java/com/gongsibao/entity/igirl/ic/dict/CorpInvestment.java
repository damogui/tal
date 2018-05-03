package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 出资方式
 * @author cyx
 *
 */
public enum CorpInvestment implements IEnum {

	MONEY(0, "货币"),
	GOODS(1, "实物"),
	OBLIGATIONS(2, "债权"),
	EARTH(3, "土地使用权"),
	STOCK(4, "股权"),
	TECH(5, "知识产权-专利技术"),
	DEDICATED(6, "知识产权-技术专有");

	private int value;
	private String text;

	CorpInvestment(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpInvestment getItem(int value) {

		for (CorpInvestment item : values()) {

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
