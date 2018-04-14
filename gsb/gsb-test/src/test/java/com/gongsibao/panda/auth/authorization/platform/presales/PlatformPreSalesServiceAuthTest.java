package com.gongsibao.panda.auth.authorization.platform.presales;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;


/*售前客服*/
public class PlatformPreSalesServiceAuthTest extends AuthBaseTest {
    @Before
    public void setup() {
        roleCode = "Platform_PreSales_Service";
        super.setup ();

    }

    protected void getResourceCodeList() {
//客户管理、订单管理（全部订单）、统计分析（客服统计）
        this.resourceNodeCodeList.add ("Operation_CRM");//客户管理
        this.resourceNodeCodeList.add ("Operation_Order_All");//订单管理(全部订单)
        this.resourceNodeCodeList.add ("Operation_CRM_STATISTICAL_CustomerService");//客服统计（好像还没人做）

    }
}
