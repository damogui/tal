package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;
import com.gongsibao.trade.web.department.DepartmentOrderPerformanceListPart;

/*订单业绩*/
public class DepartmentOrderPerformanceWorspaceTest extends SalesmanOrderPerformanceWorspaceTest {
    @Before
    public void setup() {
    	
        super.setup ();
        urlList = "/crm/order/department/performance/list";
        listPartName = formPartName = "订单业绩";
        resourceNodeCode = "Gsb_Supplier_Order_Department_Performance";
        listPartServiceController = DepartmentOrderPerformanceListPart.class.getName();
        listFilter = "";
    }
}
