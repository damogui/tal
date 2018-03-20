package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 客户类型
 * @author angang
 *
 */
public enum CustomerType  implements IEnum{

	GR(1, "个人"), QY(2, "企业");
	
	private int value;
	private String text;

	CustomerType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CustomerType getItem(int value) {

		for (CustomerType item : values()) {

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
