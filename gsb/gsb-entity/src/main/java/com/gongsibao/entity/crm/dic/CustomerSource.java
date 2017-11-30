package com.gongsibao.entity.crm.dic;

import org.codehaus.jackson.annotate.JsonCreator;
import org.netsharp.base.IEnum;

public enum CustomerSource implements IEnum{

	CUSTOMER_SOURCE_0(0, "未知"), 
	CUSTOMER_SOURCE_4181(4181, "PC官网"), 
	CUSTOMER_SOURCE_4185(4185, "400百度"), 
	CUSTOMER_SOURCE_4184(4184, "百度寻客"), 
	CUSTOMER_SOURCE_4186(4186, "在线注册"), 
	CUSTOMER_SOURCE_4171(4171, "市场活动"), 
	CUSTOMER_SOURCE_4176(4176, "阿里云"), 
	CUSTOMER_SOURCE_4201(4201, "个人挖掘"), 
	CUSTOMER_SOURCE_4114(4114, "老客户介绍"), 
	CUSTOMER_SOURCE_4111(4111, "58同城推广"), 
	CUSTOMER_SOURCE_4202(4202, "同事介绍"), 
	CUSTOMER_SOURCE_4153(4153, "老客户签单"), 
	CUSTOMER_SOURCE_41911(4191, "营销QQ"), 
	CUSTOMER_SOURCE_4182(4182, "H5官网"), 
	CUSTOMER_SOURCE_4175(4175, "腾讯平台"), 
	CUSTOMER_SOURCE_4116(4116, "渠道商"), 
	CUSTOMER_SOURCE_4192(4192, "营销微信"), 
	CUSTOMER_SOURCE_4170(4170, "业务转单"), 
	CUSTOMER_SOURCE_4115(4115, "企业信息关联"), 
	CUSTOMER_SOURCE_4117(4117, "外呼拓客"), 
	CUSTOMER_SOURCE_4168(4168, "赶集"), 
	CUSTOMER_SOURCE_4172(4172, "微信公众号"),
	CUSTOMER_SOURCE_4178(4178, "脉脉"), 
	CUSTOMER_SOURCE_4183(4183, "手机APP"), 
	CUSTOMER_SOURCE_4187(4187, "黑马会"), 
	CUSTOMER_SOURCE_4188(4188, "星河互联"), 
	CUSTOMER_SOURCE_4189(4189, "云客"), 
	CUSTOMER_SOURCE_4190(4190, "阿里旺铺"), 
	CUSTOMER_SOURCE_4193(4193, "汇桔网"), 
	CUSTOMER_SOURCE_4194(4194, " 畅捷通工作圈"), 
	CUSTOMER_SOURCE_4195(4195, "畅捷通会计家园"), 
	CUSTOMER_SOURCE_4196(4196, "用友云市场"), 
	CUSTOMER_SOURCE_4197(4197, " BOSS查账APP"), 
	CUSTOMER_SOURCE_4198(4198, "优客工场"), 
	CUSTOMER_SOURCE_4199(4199, "京东众筹"), 
	CUSTOMER_SOURCE_4200(4200, "钉钉"), 
	CUSTOMER_SOURCE_4203(4203, "万达"),
	CUSTOMER_SOURCE_4177(4177, "其他");
	
	private int value;
	private String text;

	CustomerSource(int value, String text) {
		this.value = value;
		this.text = text;
	}

	@JsonCreator
	public static CustomerSource getItem(int value) {

		for (CustomerSource item : values()) {

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

