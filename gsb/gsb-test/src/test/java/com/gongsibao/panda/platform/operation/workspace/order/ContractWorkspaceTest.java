package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class ContractWorkspaceTest extends SalesmanOrderContractWorkspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/contract/list";
        resourceNodeCode = "Operation_Order_Contract";
        listFilter = "";
    }

}
