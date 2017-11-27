package com.gongsibao.entity.franchisee.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum BusinessScope implements IEnum{

	Scope_2011(2011	, "行政审批"),
	Scope_2012(2012	, "资本运作"),
	Scope_2013(2013	, "网络营销"),
	Scope_2014(2014	, "法律服务"),
	Scope_2015(2015	, "小蜜商城"),
	Scope_2016(2016	, "知识产权"),
	Scope_2017(2017	, "财税服务"),
	Scope_2018(2018	, "工商服务"),
	Scope_2019(2019	, "公司宝典"),
//	Scope_2020(2020	, "增值服务"),
//	Scope_20110(20110	, "创业活动"),
//	Scope_20111(20111	, "合同大礼包"),
//	Scope_20112(20112	, "人事社保"),
//	Scope_20113(20113	, "公司注册"),
//	Scope_20114(20114	, "公司变更"),
//	Scope_20115(20115	, "公司年报"),
//	Scope_20120(20120	, "海外业务"),
//	Scope_20121(20121	, "企业并购"),
	Scope_20122(20122	, "专家问诊"),
	Scope_20123(20123	, "媒体运营"),
	Scope_20130(20130	, "代理记账"),
	Scope_20132(20132	, "税务服务"),
	Scope_20133(20133	, "审计报告"),
	Scope_20138(20138	, "商标"),
	Scope_20139(20139	, "著作权"),
	Scope_20140(20140	, "专利"),
//	Scope_20141(20141	, "MacBook Air"),
//	Scope_20142(20142	, "MacBook"),
//	Scope_20143(20143	, "iPhone"),
//	Scope_20144(20144	, "iPad mini"),
//	Scope_20145(20145	, "iPad Air"),
//	Scope_20146(20146	, "MacBook Pro"),
	Scope_20147(20147	, "人事劳务"),
	Scope_20148(20148	, "食品饮料"),
	Scope_20149(20149	, "餐饮美食"),
//	Scope_20150(20150	, "旅游旅行"),
//	Scope_20151(20151	, "图书音像"),
//	Scope_20152(20152	, "网络游戏"),
//	Scope_20153(20153	, "影视电影"),
//	Scope_20154(20154	, "金融备案"),
//	Scope_20155(20155	, "道路运输"),
//	Scope_20156(20156	, "个护化妆"),
//	Scope_20157(20157	, "双软高新"),
//	Scope_20158(20158	, "进出口权"),
//	Scope_20159(20159	, "母婴玩具"),
	Scope_20160(20160	, "管理体系认证"),
	Scope_20161(20161	, "医药保健"),
	Scope_20162(20162	, "增值电信"),
	Scope_20163(20163	, "企业信用"),
	Scope_20164(20164	, "系统集成"),
	Scope_20165(20165	, "招租"),
	Scope_20166(20166	, "建筑资质"),
	Scope_202001(202001	, "投融资挂牌"),
	Scope_202002(202002	, "技术服务"),
	Scope_202003(202003	, "网络营销"),
	Scope_202004(202004	, "业务课程体系"),
//	Scope_202005(202005	, "企业SaaS服务"),
//	Scope_202006(202006	, "京牌汽车"),
//	Scope_2012001(2012001	, "海外注册"),
//	Scope_2012002(2012002	, "外资业务"),
//	Scope_2012201(2012201	, "专家问诊"),
//	Scope_2012301(2012301	, "媒体运营"),
//	Scope_20200101(20200101	, "新三板挂牌"),
//	Scope_20200201(20200201	, "移动媒体系统研发"),
//	Scope_20200202(20200202	, ".com英文域名"),
//	Scope_20200203(20200203	, "400电话"),
//	Scope_20200204(20200204	, "微信独立商城"),
//	Scope_20200205(20200205	, "微信营销系统"),
	Scope_20200206(20200206	, "网站建设"),
	Scope_20200301(20200301	, "网络营销推广投放");
	private int value;
	private String text;

	BusinessScope(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static BusinessScope getItem(int value) {

		for (BusinessScope item : values()) {

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
