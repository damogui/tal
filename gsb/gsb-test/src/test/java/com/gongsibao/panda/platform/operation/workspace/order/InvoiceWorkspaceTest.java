package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceWorkspaceTest;

public class InvoiceWorkspaceTest extends SalesmanOrderInvoiceWorkspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/invoice/list";
        resourceNodeCode = "Operation_Order_Invoice";
        listFilter = "";
    }

}
