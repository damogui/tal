package com.gongsibao.entity.bd.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum AuditLogStatusType implements IEnum  {	
	
	TOAUDIT(1051, "待审核"),
	AUDITING(1052, "审核中"), 
	AUDITREJECT(1053, "驳回审核"),
	AUDITPASS(1054, "审核通过"),
	Paidui(1055, "审核排队"),
	Close(1056, "审核关闭");
	
	private int value;
	private String text;

	AuditLogStatusType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static AuditLogStatusType getItem(int value) {

		for (AuditLogStatusType item : values()) {

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
