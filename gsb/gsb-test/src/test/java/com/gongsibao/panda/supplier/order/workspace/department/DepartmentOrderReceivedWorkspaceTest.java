package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderReceivedListPart;

/*回款业绩*/
public class DepartmentOrderReceivedWorkspaceTest extends SalesmanOrderReceivedWorkspaceTest {
    @Before
    public void setup() {
        super.setup();
        urlList = "/crm/order/department/received/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Received";
        listPartServiceController = DepartmentOrderReceivedListPart.class.getName();
        listFilter = "";
    }
}
