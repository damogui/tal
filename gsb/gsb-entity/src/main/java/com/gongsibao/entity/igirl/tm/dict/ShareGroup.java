package com.gongsibao.entity.igirl.tm.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 附件类别
 */
public enum ShareGroup implements IEnum {

	CASESHARRE(0, "案件共享"), // business_liense
	SG1(1, "共享分组1"), // business_liense
	SG2(2, "共享分组2"), // trademark picture
	SG3(3, "共享分组3"), // trademark picture
	SG4(4, "共享分组4"),
	SG5(5, "共享分组5"),
	SG6(6, "共享分组6"), 
	SG7(7, "共享分组7"), 
	SG8(8, "共享分组8"), 
	SG9(9, "共享分组9"), 
	SG10(10,"共享分组10"), 
	SG11(11, "共享分组11"),
	SG12(12, "共享分组12"), 
	SG13(13, "共享分组13"),
	SG14(14,"共享分组14"), 
	SG15(15, "共享分组15"),
	SG16(16, "共享分组16"), 
	SG17(17, "共享分组17"),
	SG18(18, "共享分组18"),
	SG19(19, "共享分组19"),
	SG20(20, "共享分组20");
	private int value;
	private String text;

	ShareGroup(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static ShareGroup getItem(int value) {

		for (ShareGroup item : values()) {

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
