package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderStagingListPart;

public class DepartmentOrderStagingWorkspaceTest extends SalesmanOrderStagingWorkspaceTest {
    @Before
    public void setup() {
        super.setup();
        urlList = "/crm/order/department/staging/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Staging";
        listPartServiceController = DepartmentOrderStagingListPart.class.getName();
        listFilter = "is_installment = 1 ";
    }
}
