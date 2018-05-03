package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;
/**
 * 撰写次数
 * @author angang
 *
 */
public enum DataFeeCountType  implements IEnum{
	WU(3171, "无"), FIRST(3172, "首期一次"), LAST(3173, "末期一次"), FIRSTORLAST(3174, "首期一次末期一次"); 
	
	private int value;
	private String text;

	DataFeeCountType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DataFeeCountType getItem(int value) {

		for (DataFeeCountType item : values()) {

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
