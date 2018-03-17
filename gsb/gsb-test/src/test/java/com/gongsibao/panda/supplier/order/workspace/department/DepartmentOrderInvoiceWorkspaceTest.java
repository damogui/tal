package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderInvoiceListPart;

public class DepartmentOrderInvoiceWorkspaceTest extends SalesmanOrderInvoiceWorkspaceTest {
    @Before
    public void setup() {
        super.setup();
        urlList = "/crm/order/department/invoice/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Invoice";
        listPartServiceController = DepartmentOrderInvoiceListPart.class.getName();
    }
}
