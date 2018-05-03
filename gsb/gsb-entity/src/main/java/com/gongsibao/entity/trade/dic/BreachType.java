package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 是否有违约
 * @author angang
 *
 */
public enum BreachType  implements IEnum{

	YOU(1, "有"), WU(0, "无");
	
	private int value;
	private String text;

	BreachType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BreachType getItem(int value) {

		for (BreachType item : values()) {

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
