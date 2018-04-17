package com.gongsibao.panda.auth.authorization.supplier.order;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierOrderOperatorLeaderAuthTest  extends AuthBaseTest{
	
    @Before
    public void setup() {

        roleCode = "Supplier_Order_Operator_Leader";
        super.setup ();
    }

    protected void getResourceCodeList() {

        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Interactive");
    }
}
