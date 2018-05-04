package com.gongsibao.entity.trade.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

//发票开票公司
public enum InvoiceOpenBallotCompanyType implements IEnum  {
	
	wu(0, "无"), 
	Htzx(3071, "汉唐信通（北京）咨询股份有限公司"), 
	Htkj(3072, "汉唐信通（北京）科技有限公司"),
	Scht(3073, "四川汉唐信通商务咨询有限公司"),
	Bjwf(3074, "北京微服咨询有限公司");
	private int value;
	private String text;

	InvoiceOpenBallotCompanyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static InvoiceOpenBallotCompanyType getItem(int value) {

		for (InvoiceOpenBallotCompanyType item : values()) {

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
