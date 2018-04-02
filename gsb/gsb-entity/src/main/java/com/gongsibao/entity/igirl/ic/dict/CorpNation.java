package com.gongsibao.entity.igirl.ic.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

/**
 * 民族
 * @author cyx
 *
 */
public enum CorpNation implements IEnum {

	HAN(0, "汉族"),
	ACHANG(1, "阿昌族"),
	BAI(2, "白族"),
	BAOAN(3, "保安族"),
	BULANG(4, "布朗族"),
	BUYI(5, "布依族"),
	CHAOXIAN(6, "朝鲜族"),
	DAHANER(7, "达斡尔族"),
	DAI(8, "傣族"),
	DEANG(9, "德昂族"),
	DONGXIANG(10, "东乡族"),
	DONG(11, "侗族"),
	DULONG(12, "独龙族"),
	ELUOSI(13, "俄罗斯族"),
	ELUNCHUN(14, "鄂伦春族"),
	EWEBKE(15, "鄂温克族"),
	GAOSHAN(16, "高山族"),
	HANI(17, "哈尼族"),
	HASAKE(18, "哈萨克族"),
	HEZE(19, "赫哲族"),
	HUI(20, "回族"),
	JINUO(21, "基诺族"),
	JING(22, "京族"),
	JINGPO(23, "景颇族"),
	KEERKEZI(24, "柯尔克孜族"),
	LAGU(25, "拉祜族"),
	LI(26, "黎族"),
	LISU(27, "傈僳族"),
	MAN(28, "满族"),
	MAONAN(29, "毛南族"),
	MENBA(30, "门巴族"),
	MENGGU(31, "蒙古族"),
	MIAO(32, "苗族"),
	NAXI(33, "纳西族"),
	NU(34, "怒族"),
	PUMI(35, "普米族"),
	QIANG(36, "羌族"),
	SALA(37, "撒拉族"),
	SHUI(38, "水族"),
	TAJIKE(39, "塔吉克族"),
	TATAER(40, "塔塔尔族"),
	TUJIA(41, "土家族"),
	TU(42, "土族"),
	WEIWUER(43, "维吾尔族"),
	WUZIBIEKE(44, "乌孜别克族"),
	ZIBO(45, "锡伯族"),
	YAO(46, "瑶族"),
	YI(47, "彝族"),
	YUGU(48, "裕固族"),
	ZANG(49, "藏族"),
	ZHUANG(50, "壮族"),
	QILAO(51, "仡佬族"),
	MOLAO(52, "仫佬族"),
	WA(53, "佤族"),
	LUOBA(54, "珞巴族"),
	SHE(55, "畲族"),
	OTHER(56, "其他"),
	FOREIGN(57, "外国血统");

	private int value;
	private String text;

	CorpNation(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CorpNation getItem(int value) {

		for (CorpNation item : values()) {

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
