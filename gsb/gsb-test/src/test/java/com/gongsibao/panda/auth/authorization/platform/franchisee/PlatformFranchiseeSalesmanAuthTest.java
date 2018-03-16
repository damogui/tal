package com.gongsibao.panda.auth.authorization.platform.franchisee;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformFranchiseeSalesmanAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		super.setup();
		roleCode = "Platform_Franchisee_Salesman";
	}
}
