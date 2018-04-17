package com.gongsibao.panda.auth.authorization.platform.franchisee;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;


/*招商_部门管理员 最大（招商角色）*/
public class PlatformFranchiseeAdminAuthTest extends AuthBaseTest {
    @Before
    public void setup() {
        roleCode = "Platform_Franchisee_Admin";
        super.setup();

    }

    protected void getResourceCodeList() {
        this.resourceNodeCodeList.add("GSB_BD");//系统设置

    }


}
