package com.gongsibao.entity.bd.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum DictType implements IEnum  {

	Diqu(101, "地区"), 
	Yhlx(102, "用户类型"),
	Hyzckhdlx(104, "审核类型"), 
	Shzt(105, "审核状态"), 
	Cplbxst(106, "产品列表显示图"), 
	Ywylx(107, "业务员类型"),
	Cpfl(201, "产品分类"), 
	Cpdw(202, "产品单位"), 
	Cpfwlx(203, "产品服务类型"), 
	Cpdjshzt(204, "产品定价审核状态"), 
	Cpxsflx(205, "产品销售方类型"), 
	Cpcllcjdlx(206, "产品处理流程节点类型"), 
	Cpfwtx(207, "产品服务特性"), 
	Ddfkzt(301, "订单付款状态"), 
	Ddclzt(302, "订单处理状态"), 
	Ddtkzt(303, "订单退款状态"), 
	Ddly(304, "订单来源"), 
	Ddzlzt(305, "订单项处理状态"), 
	Ddxhyhgxlx(306, "订单项和用户关系类型"), 
	Kpgs(307, "开票公司"), 
	Fblx(308, "发票类型"), 
	Ddyhlx(309, "订单优惠类型"), 
	Zffkfs(310, "支付付款方式"), 
	Xxfkfs(311, "线下付款方式"), 
	Zfcgzt(312, "支付成功状态"), 
	Tkfkfs(313, "退款付款方式"), 
	Ddxhyhgxzt(314, "订单项和用户关系状态"), 
	Ddxjllx(315, "订单项记录类型"),
	CRMkhgjzt(401, "CRM客户跟进状态"),
	khzycd(402, "客户重要程度"),
	CRMKhly(411, "CRM客户来源"),
	CRMZxtj(421, "CRM咨询途径"),
	Jyfw(431, "经营范围"),
	Gszzxs(441, "公司组织形式"),
	Yhqdfffs(451, "优惠券的发放方式"),
	Yhqzkz(461, "优惠券折扣值"),
	Yhqsypt(471, "优惠券使用平台"),
	Yhqsplx(481, "优惠券商品类型"),
	Qhwxzf(482, "切换微信支付"),
	Qhzfbzf(483, "切换支付宝支付"),
	Qhgrwyzf(484, "切换个人网银支付"),
	Qhqywyzf(485, "切换企业网银支付"),
	Qyxxcxlb(494, "企业信息查询类别"),
	Fhssbm(499, "费用归属部门"),
	Ddscdalx(500, "钉钉上传档案类型"),
	Hy(501, "行业"),
	Ddsblbdydjylx(502, "钉钉商标类别对应的经营类型"),
	Xzlhyhjyfwgjz(509, "新整理行业和经营范围关键字");
	
	
	
	private int value;
	private String text;

	DictType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static DictType getItem(int value) {

		for (DictType item : values()) {

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
