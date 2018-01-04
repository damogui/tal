package com.gongsibao.entity.bd.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AuditLogType implements IEnum  {		
	
	Cpdj(1041, "产品定价申请审核"),
	Ddgj(1042, "订单改价申请审核"), 
	Htsq(1043, "合同申请审核"),
	Fbsq(1044, "发票申请审核"),
	Sksq(1045, "收款申请审核"),
	Tdsq(1046, "退单申请审核"),
	Fqsq(1047, "分期申请审核"),
	Cpgj(1048, "产品改价申请审核");
	
	private int value;
	private String text;

	AuditLogType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AuditLogType getItem(int value) {

		for (AuditLogType item : values()) {

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
