package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 是否特殊行业Special
 * @author cyx
 *
 */
public enum CorpSpecial implements IEnum {

	NONE(0, "不是特殊行业"),
	PROXY(1, "登记注册代理机构"),
	FUTURES(2, "期货经纪机构"),
	FOUNFATION(3, "投资基金(不含基金管理公司)"),
	BABY(4, "母婴护理企业"),
	BURE(5, "煤矿"),
	TRADE(6, "交易类企业(含交易类企业分支机构)");
	private int value;
	private String text;

	CorpSpecial(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpSpecial getItem(int value) {

		for (CorpSpecial item : values()) {

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
