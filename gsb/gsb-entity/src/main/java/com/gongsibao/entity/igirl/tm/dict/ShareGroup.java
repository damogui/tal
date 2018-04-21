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
	SG20(20, "共享分组20"),
	SG21(21, "共享分组21"),
	SG22(22,"共享分组22"), 
	SG23(23, "共享分组23"),
	SG24(24, "共享分组24"), 
	SG25(25, "共享分组25"),
	SG26(26, "共享分组26"),
	SG27(27, "共享分组27"),
	SG28(28, "共享分组28"),
	SG29(29, "共享分组29"),
	SG30(30, "共享分组30");
//	SG31(31, "共享分组31"),
//	SG32(32,"共享分组32"), 
//	SG33(33, "共享分组33"),
//	SG34(34, "共享分组34"), 
//	SG35(35, "共享分组35"),
//	SG36(36, "共享分组36"),
//	SG37(37, "共享分组37"),
//	SG38(38, "共享分组38"),
//	SG39(39, "共享分组39"),
//	SG40(40, "共享分组40"),
//	SG41(41, "共享分组41"),
//	SG42(42,"共享分组42"), 
//	SG43(43, "共享分组43"),
//	SG44(44, "共享分组44"), 
//	SG45(45, "共享分组45"),
//	SG46(46, "共享分组46"),
//	SG47(47, "共享分组47"),
//	SG48(48, "共享分组48"),
//	SG49(49, "共享分组49"),
//	SG50(50, "共享分组50");
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
