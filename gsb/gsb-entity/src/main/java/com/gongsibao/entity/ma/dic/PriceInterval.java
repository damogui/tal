package com.gongsibao.entity.ma.dic;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum PriceInterval implements IEnum {

	OneUnder(1, "1万以下"), OneToThree(2, "1万-3万"),ThreeToFive(3, "3万-5万"), FiveToTen(4, "5万-10万"), TenAbove(5, "10万以上");
	private int value;
	private String text;

	PriceInterval(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static PriceInterval getItem(int value) {

		for (PriceInterval item : values()) {

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

	public static PriceInterval getByPrice(BigDecimal price) {

		if (price.compareTo(new BigDecimal(10000)) ==-1) {

			return PriceInterval.OneUnder;
		}
		if (price.compareTo(new BigDecimal(10000)) ==1 && price.compareTo(new BigDecimal(30000)) ==-1) {

			return PriceInterval.OneToThree;
		}
		if (price.compareTo(new BigDecimal(30000)) ==1 && price.compareTo(new BigDecimal(50000)) ==-1) {

			return PriceInterval.ThreeToFive;
		}
		if (price.compareTo(new BigDecimal(50000)) ==1 && price.compareTo(new BigDecimal(100000)) ==-1) {

			return PriceInterval.FiveToTen;
		}
		if (price.compareTo(new BigDecimal(100000)) ==1) {

			return PriceInterval.TenAbove;
		}
		return null;
	}
}
