package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;

public class PerformanceWorkspaceTest extends SalesmanOrderPerformanceWorspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/performance/list";
        resourceNodeCode = "Operation_Order_Performance";
        listFilter = "";
    }

}
