package com.gongsibao.panda.auth.authorization.supplier.crm;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierCrmSalesmanAuthTest extends AuthBaseTest{
	
	@Before
	public void setup() {

		roleCode = "Supplier_Salesman";
		super.setup();
	}
	
	protected void getResourceCodeList() {

		this.resourceNodeCodeList.add("GSB_CRM_MY");
	}
}
