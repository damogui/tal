package com.gongsibao.panda.auth.authorization.platform.finance;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformFinanceSalesmanAuditTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Finance_Salesman";
		super.setup();

	}
}
