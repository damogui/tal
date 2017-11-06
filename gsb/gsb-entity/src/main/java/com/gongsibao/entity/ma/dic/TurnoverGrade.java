package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw 流水区间
 */
public enum TurnoverGrade implements IEnum {

	UNLIMITED(1, "不限"), 
	GRADE_01(2, "100万-500万"), 
	GRADE_02(3, "500万-1000万"), 
	GRADE_03(4, "1000万-3000万"), 
	GRADE_04(5, "3000万-5000万"), 
	GRADE_05(6,"5000万-1亿"), 
	GRADE_06(7, "1亿-5亿"), 
	OTHER(8, "其它");
	
	
	private int value;
	private String text;

	TurnoverGrade(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static TurnoverGrade getItem(int value) {

		for (TurnoverGrade item : values()) {

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
