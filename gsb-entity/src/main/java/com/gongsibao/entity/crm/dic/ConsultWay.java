package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum ConsultWay implements IEnum{
	
	CONSULT_WAY_4211(4211, "400电话"), 
	CONSULT_WAY_4212(4212, "在线客服"), 
	CONSULT_WAY_4213(4213, "企业QQ"), 
	CONSULT_WAY_4214(4214, "PC官网"), 
	CONSULT_WAY_4215(4215, "H5官网"), 
	CONSULT_WAY_4216(4216, "手机APP"),
	CONSULT_WAY_4217(4217, "工作手机"),
	CONSULT_WAY_4218(4218, "工作QQ"),
	CONSULT_WAY_42118(42118, "微信"),
	CONSULT_WAY_42137(42137, "乐语留言"), 
	CONSULT_WAY_42138(42138, "乐语在线"), 
	CONSULT_WAY_42139(42139, "400留言"), 
	CONSULT_WAY_42140(42140, "小能在线"), 
	CONSULT_WAY_42141(42141, "小能留言"), 
	CONSULT_WAY_42142(42142, "注册"), 
	CONSULT_WAY_42143(42143, "商务通"), 
	CONSULT_WAY_4219(4219, "其他");
	private int value;
	private String text;
	ConsultWay(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ConsultWay getItem(int value) {

		for (ConsultWay item : values()) {

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