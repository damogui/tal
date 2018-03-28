package com.gongsibao.panda.auth.authorization.platform.finance;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
/*平台 财务_总监*/
public class PlatformFinanceAdminAuditTest extends AuthBaseTest {

	@Before
	public void setup() {
        roleCode = "Platform_Finance_Admin";
		super.setup();

	}
}
