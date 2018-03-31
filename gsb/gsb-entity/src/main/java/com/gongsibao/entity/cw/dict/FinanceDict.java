package com.gongsibao.entity.cw.dict;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public class FinanceDict {

	/*
	 * 财务单据审批
	 */
	public static enum AuditStatus implements IEnum {
		Status_1(1, "待审批"),
		Status_2(2, "审批中"),
		Status_3(3, "审批通过"),
		Status_4(4, "被驳回");
		private int value;
		private String text;

		AuditStatus(int value, String text) {
			this.value = value;
			this.text = text;
		}
		@JsonCreator
		public static AuditStatus getItem(int value) {
			for (AuditStatus item : values()) {
				if (item.getValue() == value) {
					return item;
				}
			}
			return null;
		}

		public String getText() {
			return this.text;
		}
		public Integer getValue() {
			return this.value;
		}
	}
	/*
	 * 借款单据类型
	 */
	public static enum LoanBillType implements IEnum{
		LoanType_1(1, "日常借款"),
		LoanType_2(2, "招待费借款"),
		LoanType_3(3, "差旅费借款");
		private int value;
		private String text;

		LoanBillType(int value, String text) {
			this.value = value;
			this.text = text;
		}
		@JsonCreator
		public static LoanBillType getItem(int value) {
			for (LoanBillType item : values()) {
				if (item.getValue() == value) {
					return item;
				}
			}
			return null;
		}

		public String getText() {
			return this.text;
		}
		public Integer getValue() {
			return this.value;
		}
	} 
	/*
	 * 支付方式枚举
	 */
	public static enum PaymentMethod implements IEnum{
		XJ(1, "现金"),
		ZZ(2, "转账"),
		ZP(3, "支票");
		
		private int value;
		private String text;

		PaymentMethod(int value, String text) {
			this.value = value;
			this.text = text;
		}
		@JsonCreator
		public static PaymentMethod getItem(int value) {
			for (PaymentMethod item : values()) {
				if (item.getValue() == value) {
					return item;
				}
			}
			return null;
		}

		public String getText() {
			return this.text;
		}
		public Integer getValue() {
			return this.value;
		}
	}
	/*
	 * 费用类型
	 */
	public static enum CostType implements IEnum{
		FGSBYJ(1, "分公司备用金"),
		BGYP(2, "办公用品"),
		FGSSBGJJ(3, "分公司社保、公积金"),
		CGJK(4, "采购借款"),
		JTF(5, "交通费"),
		SDF(6, "水电费"),
		TXF(7, "通讯费"),
		QCF(8, "汽车费用"),
		BMHDF(9, "部门活动费"),
		LDF(10, "劳务费"),
		ZPF(11, "招聘费"),
		ZJF(12, "中介费"),
		ZXF(13, "咨询费"),
		SSF(14, "诉讼费"),
		FZWYCN(15, "房租、物业、采暖"),
		WF(16, "网费"),
		KDF(17, "快递费"),
		HYF(18, "会议费"),
		SBZJ(19, "设备租金"),
		SBXLF(20, "设备修理费"),
		ZGFLF(21, "职工福利费"),
		SYBXF(22, "商业保险费"),
		CAZSGBF(23, "CA证书工本费"),
		TJF(24, "团建费"),
		ZXF1(25, "装修费"),
		XBGJJ(26, "社保、公积金"),
		RJFWF(27, "软件服务费"),
		WBFWF(28, "外包服务费"),
		QT(29, "其他");
		private int value;
		private String text;

		CostType(int value, String text) {
			this.value = value;
			this.text = text;
		}
		@JsonCreator
		public static CostType getItem(int value) {
			for (CostType item : values()) {
				if (item.getValue() == value) {
					return item;
				}
			}
			return null;
		}

		public String getText() {
			return this.text;
		}
		public Integer getValue() {
			return this.value;
		}
	}
	//单据类型
	public static enum FormType implements IEnum{
		JKD(1,"借款单"),
		BXD(2,"报销单"),
		FKD(3,"借款单");
		
		private int value;
		private String text;

		FormType(int value, String text) {
			this.value = value;
			this.text = text;
		}
		@JsonCreator
		public static FormType getItem(int value) {
			for (FormType item : values()) {
				if (item.getValue() == value) {
					return item;
				}
			}
			return null;
		}

		public String getText() {
			return this.text;
		}
		public Integer getValue() {
			return this.value;
		}
	}
	
	//单据类型
		public static enum AuditDetailStatus implements IEnum{
			WAIT(1,"待审核"),
			AGREE(2,"通过"),
			REJECT(3,"驳回");
			
			private int value;
			private String text;

			AuditDetailStatus(int value, String text) {
				this.value = value;
				this.text = text;
			}
			@JsonCreator
			public static AuditDetailStatus getItem(int value) {
				for (AuditDetailStatus item : values()) {
					if (item.getValue() == value) {
						return item;
					}
				}
				return null;
			}

			public String getText() {
				return this.text;
			}
			public Integer getValue() {
				return this.value;
			}
		}
		
		/*
		 * 报销单据类型
		 */
		public static enum ExpenseBillType implements IEnum{
			ExpenseType_1(1, "市场费报销"),
			ExpenseType_2(2, "招待费报销"),
			ExpenseType_3(3, "差旅费报销");
			private int value;
			private String text;

			ExpenseBillType(int value, String text) {
				this.value = value;
				this.text = text;
			}
			@JsonCreator
			public static ExpenseBillType getItem(int value) {
				for (ExpenseBillType item : values()) {
					if (item.getValue() == value) {
						return item;
					}
				}
				return null;
			}

			public String getText() {
				return this.text;
			}
			public Integer getValue() {
				return this.value;
			}
		} 
		//补助类别
		public static enum SubsidyType implements IEnum{
			SubsidyType_1(1, "出差补助"),
			SubsidyType_2(2, "交通补助"),
			SubsidyType_3(3, "餐补"),
			SubsidyType_4(4, "话费补助");
			private int value;
			private String text;

			SubsidyType(int value, String text) {
				this.value = value;
				this.text = text;
			}
			@JsonCreator
			public static SubsidyType getItem(int value) {
				for (SubsidyType item : values()) {
					if (item.getValue() == value) {
						return item;
					}
				}
				return null;
			}

			public String getText() {
				return this.text;
			}
			public Integer getValue() {
				return this.value;
			}
		}
		//业务类型
		public static enum BusinessType implements IEnum {
			CAZS(1, "CA证书"),
			DZF(2, "地址费"),
			KZF(3, "刻章费"),
			ZSCQ(4, "知识产权（商标）"),
			GF(5, "规费"),
			DSDF(6, "代收代付（CA证书、刻章、地址费））"),
			DLF(7, "代理费"),
			CLZXF(8, "材料撰写费"),
			LWF(9, "劳务费"),
			IDC_ISP(10, "IDC/ISP系统"),
			BGQ(11, "并购款"),
			QT(4, "其它");
			
			
			private int value;
			private String text;

			BusinessType(int value, String text) {
				this.value = value;
				this.text = text;
			}
			@JsonCreator
			public static BusinessType getItem(int value) {
				for (BusinessType item : values()) {
					if (item.getValue() == value) {
						return item;
					}
				}
				return null;
			}

			public String getText() {
				return this.text;
			}
			public Integer getValue() {
				return this.value;
			}
		}
}
