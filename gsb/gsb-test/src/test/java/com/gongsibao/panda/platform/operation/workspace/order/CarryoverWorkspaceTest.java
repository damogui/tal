package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderCarryoverWorkspaceTest;

public class CarryoverWorkspaceTest extends SalesmanOrderCarryoverWorkspaceTest {

    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/carryover/list";
        resourceNodeCode = "Operation_Order_Carryover";
        listFilter = "";
    }

    
}
