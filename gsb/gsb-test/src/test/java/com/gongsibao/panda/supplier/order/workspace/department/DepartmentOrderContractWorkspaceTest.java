package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderContractListPart;

public class DepartmentOrderContractWorkspaceTest extends SalesmanOrderContractWorkspaceTest {

    @Before
    public void setup() {
        super.setup();
        urlList = "/crm/order/department/contract/list";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Contract";
        listPartServiceController = DepartmentOrderContractListPart.class.getName();
    }
}
