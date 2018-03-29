package com.gongsibao.panda.auth.authorization.platform.operation;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformOperationLeaderAuditTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Operation_Leader";
		super.setup();

	}
}
