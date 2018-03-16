package com.gongsibao.panda.auth.authorization.platform.presales;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformPreSalesServiceAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		super.setup();
		roleCode = "Platform_Finance_Admin";
	}
}
