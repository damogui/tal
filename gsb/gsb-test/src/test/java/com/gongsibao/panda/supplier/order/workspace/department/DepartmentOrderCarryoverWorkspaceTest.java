package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderCarryoverWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderCarryoverListPart;

public class DepartmentOrderCarryoverWorkspaceTest extends SalesmanOrderCarryoverWorkspaceTest {
    @Before
    public void setup() {
        super.setup();
        urlList = "/crm/order/department/carryover/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Carryover";
        listPartServiceController = DepartmentOrderCarryoverListPart.class.getName();
        listFilter = "";
        
    }
}
