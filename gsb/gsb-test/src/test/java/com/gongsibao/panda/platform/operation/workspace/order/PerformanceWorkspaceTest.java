package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class PerformanceWorkspaceTest extends SalesmanOrderPerformanceWorspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/performance/list";
        resourceNodeCode = "Operation_Order_Performance";
        listFilter = "";
    }

}
