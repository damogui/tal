package com.gongsibao.panda.auth.authorization.supplier.order;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;
/*订单业务员授权*/
public class SupplierOrderSalesmanAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		roleCode = "Supplier_Order_Salesman";
		super.setup();
	}
	
	protected void getResourceCodeList() {

		this.resourceNodeCodeList.add("Gsb_Supplier_Order_Salesman");
	}
}
