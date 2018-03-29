package com.gongsibao.panda.auth.authorization.supplier.crm;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/*crm 员工*/
public class SupplierCrmSalesmanAuthTest extends AuthBaseTest{
	
	@Before
	public void setup() {

		roleCode = "Supplier_Salesman";
		super.setup();
	}
	
	protected void getResourceCodeList() {

		this.resourceNodeCodeList.add("GSB_CRM_MY");
        this.resourceNodeCodeList.add ("Gsb_Supplier_Order_Salesman");//我的订单
        this.resourceNodeCodeList.add("GSB_CRM_STATISTICAL");//统计分析：
	}
}
