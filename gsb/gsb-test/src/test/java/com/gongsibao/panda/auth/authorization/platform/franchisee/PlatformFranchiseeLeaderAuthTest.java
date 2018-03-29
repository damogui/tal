package com.gongsibao.panda.auth.authorization.platform.franchisee;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformFranchiseeLeaderAuthTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Franchisee_Leader";
		super.setup();

	}
}
