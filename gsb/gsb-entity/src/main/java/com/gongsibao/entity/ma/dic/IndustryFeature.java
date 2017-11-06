package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 行业特点
 */
public enum IndustryFeature implements IEnum {

	IF01(1, "信息科技"), IF02(2, "商贸贸易"), IF03(3, "咨询管理"), IF04(4, "金融投资"), IF05(5,
			"文化传媒"), IF06(6, "人力劳务"), IF07(7, "医疗医药"), IF08(8, "食品餐饮"), IF09(9,
			"影视传媒"), IF10(10, "旅游旅行"), IF11(11, "货运物流"), IF12(12, "保险保全"), IF13(13,
			"著作权"), IF14(15, "建筑施工"), IF15(16, "其它");
	private int value;
	private String text;

	IndustryFeature(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static IndustryFeature getItem(int value) {

		for (IndustryFeature item : values()) {

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
