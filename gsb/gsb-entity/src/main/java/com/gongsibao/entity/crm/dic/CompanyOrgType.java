package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CompanyOrgType implements IEnum{

	//公司组织形式(441) 44101有限公司、44102有限合伙公司、44103股份有限公司、44104分公司、44105集团公司、44106个体户、44107中外合资公司、44108外商独资公司、44109外资代表处
	TYPE_44101(44101, "有限公司"), 
	TYPE_44102(44102, "有限合伙公司"),
	TYPE_44103(44103, "股份有限公司"),
	TYPE_44104(44104, "分公司"),
	TYPE_44105(44105, "集团公司"),
	TYPE_44106(44106, "个体户"),
	TYPE_44107(44107, "中外合资公司"),
	TYPE_44108(44108, "外商独资公司"),
	TYPE_44109(44109, "外资代表处");
	private int value;
	private String text;

	CompanyOrgType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CompanyOrgType getItem(int value) {

		for (CompanyOrgType item : values()) {

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
