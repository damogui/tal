package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 合同类型
 * @author angang
 *
 */
public enum ContractType  implements IEnum{

	DZ(1, "电子"), ZZ(0, "纸质");
	
	private int value;
	private String text;

	ContractType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ContractType getItem(int value) {

		for (ContractType item : values()) {

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
