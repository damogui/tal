package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceWorkspaceTest;
import com.gongsibao.trade.web.platform.InvoiceListPart;

public class InvoiceWorkspaceTest extends SalesmanOrderInvoiceWorkspaceTest {
    @Before
    public void setup() {

        super.setup();
        urlList = "/operation/order/invoice/list";
        resourceNodeCode = "Operation_Order_Invoice";
        listFilter = "";
        listPartServiceController = InvoiceListPart.class.getName();
    }

}
