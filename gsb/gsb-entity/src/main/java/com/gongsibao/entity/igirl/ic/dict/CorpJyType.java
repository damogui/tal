package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 检验检疫企业类别
 * @author cyx
 *
 */
public enum CorpJyType implements IEnum {

	JZY(0, "进境植物产品运输单位"),
	CZY(1, "出境植物产品仓储单位"),
	JZG(2, "进境植物隔离场"),
	CZG(3, "出境植物隔离场"),
	JZZ(4, "进境植物种植场"),
	CJJ(5, "出境植物种植场"),
	OTHER(6, "其他"),
	WM(7, "外贸企业"),
	ZY(8, "有自营权的生产企业"),
	JZX(9, "集装箱场站"),
	JZX2(10, "集装箱场站"),
	CSC(11, "出口货物生产企业"),
	DBJ(12, "代理报检单位"),
	XZ(13, "熏蒸单位"),
	NDD(14, "国内定点加工厂"),
	WDD(15, "国外定点加工厂"),
	PCL(16, "配餐料使用单位"),
	JDC(17, "进境动物产品仓储单位"),
	CDC(18, "出境动物产品仓储单位"),
	JDY(19, "进境动物产品运输单位"),
	CDY(20, "出境动物产品运输单位"),
	JDG(21, "进境动物隔离场"),
	CDG(22, "出境动物隔离场"),
	JDYZ(23, "进境动物养殖场"),
	CDYZ(24, "出境动物养殖场"),
	JZCC(25, "进境植物产品仓储单位"),
	CZCC(26, "出境植物产品运输单位");


	private int value;
	private String text;

	CorpJyType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpJyType getItem(int value) {

		for (CorpJyType item : values()) {

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
