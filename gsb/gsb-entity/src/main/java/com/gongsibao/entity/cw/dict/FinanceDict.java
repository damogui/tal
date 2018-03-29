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
		BGYP(1, "办公用品"),
		JTF(2, "交通费"),
		SDF(3, "水电费"),
		ZXF(4, "装修费"),
		TJF(5, "团建费"),
		WBFWF(6, "外包服务费"),
		RJFWF(7, "软件服务电费"),
		ZIXF(8, "咨询费"),
		ZPF(9, "招聘费"),
		LDF(10, "劳动费"),
		WANGF(11, "网费"),
		ZDF(12, "招待费"),
		YGFLF(13, "员工福利费"),
		HYF(14, "会议费"),
		SBXLF(15, "设备修理费"),
		SBZJF(16, "设备租金费"),
		KDF(17, "快递费"),
		QT(18, "其他");
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
}
