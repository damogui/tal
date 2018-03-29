package com.gongsibao.panda.auth.authorization.platform.ma;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class PlatformMaLeaderAuthTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Ma_Leader";
		super.setup();

	}
}
