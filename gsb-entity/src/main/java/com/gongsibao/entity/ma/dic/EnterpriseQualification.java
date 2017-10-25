package com.gongsibao.entity.ma.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * @author hw
 * 企业资质类型
 */
public enum EnterpriseQualification implements IEnum{

	EQ01(1, "ICP许可证"), 
	EQ02(2, "SP许可证"), 
	EQ03(3, "IDC，ISP许可证"), 
	EQ04(4, "电信码号"), 
	EQ05(5,"网络文化经营许可证"), 
	EQ06(6, "游戏运营备案"), 
	EQ07(7, "私募基金管理人备案"), 
	EQ08(8, "食堂经营许可证"), 
	EQ09(9,"人力资源服务许可证"), 
	EQ10(10, "劳务派遣许可证"), 
	EQ11(11, "广播电视节目制作许可证"), 
	EQ12(12, "国内旅行社"), 
	EQ13(13,"国际旅行社"), 
	EQ14(14, "道路运输许可证"), 
	EQ15(15, "高新认证"),
	EQ16(16, "医疗器械经营许可证二级"),
	EQ17(17, "医疗器械经营许可证三级"),
	EQ18(18, "进出口权审批");
	private int value;
	private String text;

	EnterpriseQualification(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static EnterpriseQualification getItem(int value) {

		for (EnterpriseQualification item : values()) {

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
