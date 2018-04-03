package com.gongsibao.panda.auth.authorization.supplier.order;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/*订单业务员授权  服务商业务员*/
public class SupplierOrderSalesmanAuthTest extends AuthBaseTest {
    @Before
    public void setup() {

        roleCode = "Supplier_Order_Salesman";
        super.setup ();
    }

    protected void getResourceCodeList() {
//客户管理（我的商机）、订单管理（我的订单）、统计分析（综合统计、漏斗统计、跟进统计）
        this.resourceNodeCodeList.add("GSB_CRM_MY");//客户管理 我的商机
        this.resourceNodeCodeList.add ("Gsb_Supplier_Order_Salesman");//我的订单
        this.resourceNodeCodeList.add("GSB_CRM_STATISTICAL");//统计分析：
        //this.resourceNodeCodeList.add ("Operation_Order_Contract");//创建合同

    }
}
