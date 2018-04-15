package com.gongsibao.panda.auth.authorization.platform.presales;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;


/*售前经理*/
public class PlatformPreSalesLeaderAuthTest extends AuthBaseTest{
	@Before
	public void setup() {
        roleCode = "Platform_PreSales_Leader";
		super.setup();

	}
    protected void getResourceCodeList() {
        this.resourceNodeCodeList.add("Operation_CRM");//客户管理 我的商机

        this.resourceNodeCodeList.add("GSB_Trade_Manage");//订单管理
        this.resourceNodeCodeList.add("Operation_CRM_STATISTICAL");//统计分析
        this.resourceNodeCodeList.add("GSB_Supplier");//服务商管理
        this.resourceNodeCodeList.add ("ChangePassword");//密码权限



    }
}
