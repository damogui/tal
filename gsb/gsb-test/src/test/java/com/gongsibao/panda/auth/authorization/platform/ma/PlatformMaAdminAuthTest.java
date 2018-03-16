package com.gongsibao.panda.auth.authorization.platform.ma;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformMaAdminAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		super.setup();
		roleCode = "Platform_Ma_Admin";
	}
}
