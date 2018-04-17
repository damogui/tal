package com.gongsibao.panda.auth.authorization.supplier.igirl;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierIgirlSalesmanAuthTest extends AuthBaseTest{
	
	@Before
	public void setup() {

		roleCode = "IGIRL_Salesman";
		super.setup();
	}
	
	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("IGIRL_My_TradeMarkCase");//我的方案
		this.resourceNodeCodeList.add("IGIRL_My_TradeMark");//我的跟进
		//this.resourceNodeCodeList.add("GSB_IC_AI");//智能工商
		this.resourceNodeCodeList.add("IGIRL_FollowNotice_TradeMark");//我的异常商标
	}
}
