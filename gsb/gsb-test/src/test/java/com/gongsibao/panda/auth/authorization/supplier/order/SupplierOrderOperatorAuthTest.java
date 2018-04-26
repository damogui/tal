package com.gongsibao.panda.auth.authorization.supplier.order;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/*订单_操作员*/
public class SupplierOrderOperatorAuthTest extends AuthBaseTest{
	
    @Before
    public void setup() {

        roleCode = "Supplier_Order_Operator";
        super.setup ();
    }

    protected void getResourceCodeList() {

        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Interactive_My_In_Charge");
        this.resourceNodeCodeList.add ("CRM_CompanyIntention");//新增企业信息
    }
}
