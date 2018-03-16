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

		this.resourceNodeCodeList.add("GSB_CRM");
	}
}
