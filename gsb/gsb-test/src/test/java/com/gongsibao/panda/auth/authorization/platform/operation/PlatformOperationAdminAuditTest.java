package com.gongsibao.panda.auth.authorization.platform.operation;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformOperationAdminAuditTest extends AuthBaseTest{
	@Before
	public void setup() {

		super.setup();
		roleCode = "Platform_Operation_Admin";
	}
}
