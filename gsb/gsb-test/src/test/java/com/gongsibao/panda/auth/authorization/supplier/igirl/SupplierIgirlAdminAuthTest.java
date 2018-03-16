package com.gongsibao.panda.auth.authorization.supplier.igirl;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierIgirlAdminAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		roleCode = "IGIRL_Admin";
		super.setup();
	}
	
	protected void getResourceCodeList() {

		this.resourceNodeCodeList.add("GSB_TRADE_AI");
		this.resourceNodeCodeList.add("GSB_CRM_SYS");
	}
}
