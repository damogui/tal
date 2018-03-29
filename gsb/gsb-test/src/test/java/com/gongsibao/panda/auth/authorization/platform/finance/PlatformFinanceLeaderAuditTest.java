package com.gongsibao.panda.auth.authorization.platform.finance;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
/*财务_经理*/
public class PlatformFinanceLeaderAuditTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Finance_Leader";
		super.setup();

	}
}
