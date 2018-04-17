package com.gongsibao.panda.auth.authorization.supplier.igirl;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierIgirlAuditAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		roleCode = "IGIRL_Audit";
		super.setup();
	}
	
	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("IGIRL_All_TradeMarkCase");//方案生成
		this.resourceNodeCodeList.add("IGIRL_All_TradeMark");//进度跟进
		this.resourceNodeCodeList.add("IGIRL_AllFollowNotice_TradeMark");//异常商标
	}
}
