package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;

public class ContractWorkspaceTest extends SalesmanOrderContractWorkspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/contract/list";
        resourceNodeCode = "Operation_Order_Contract";
        listFilter = "";
    }

}
