package com.gongsibao.panda.auth.authorization.platform.franchisee;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
/*招商_业务员（招商角色）*/
public class PlatformFranchiseeSalesmanAuthTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Franchisee_Salesman";
		super.setup();

	}

	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("GSB_BD_MY");//客户管理

	}
}
