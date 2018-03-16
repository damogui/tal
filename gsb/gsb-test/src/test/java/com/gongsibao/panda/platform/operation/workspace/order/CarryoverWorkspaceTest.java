package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderCarryoverWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class CarryoverWorkspaceTest extends SalesmanOrderCarryoverWorkspaceTest {

    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/carryover/list";
        resourceNodeCode = "Operation_Order_Carryover";
        listFilter = "";
    }

}
