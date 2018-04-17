package com.gongsibao.panda.auth.authorization.platform.franchisee;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;


/*招商_部门负责人 老二（招商角色）*/
public class PlatformFranchiseeLeaderAuthTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_Franchisee_Leader";
		super.setup();

	}
	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("GSB_BD_MY");//客户管理
		this.resourceNodeCodeList.add("GSB_BD_DEPARTMENT");//客户管理


	}
}
