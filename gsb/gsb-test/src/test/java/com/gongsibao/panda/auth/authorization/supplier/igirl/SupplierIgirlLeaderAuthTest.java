package com.gongsibao.panda.auth.authorization.supplier.igirl;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierIgirlLeaderAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		roleCode = "IGIRL_Leader";
		super.setup();
	}
	
	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("IGIRL_My_TradeMarkCase");//我的方案
		this.resourceNodeCodeList.add("IGIRL_My_TradeMark");//我的跟进
		this.resourceNodeCodeList.add("IGIRL_Dp_TradeMarkCase");//部门方案
		this.resourceNodeCodeList.add("IGIRL_Dp_TradeMark");//部门跟进
		this.resourceNodeCodeList.add("IGIRL_DpFollowNotice_TradeMark");//部门异常商标
		this.resourceNodeCodeList.add("IGIRL_FollowNotice_TradeMark");//我的异常商标
	}
}
