package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class InvoiceWorkspaceTest extends SalesmanOrderInvoiceWorkspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/invoice/list";
        resourceNodeCode = "Operation_Order_Invoice";
        listFilter = "";
    }

}
