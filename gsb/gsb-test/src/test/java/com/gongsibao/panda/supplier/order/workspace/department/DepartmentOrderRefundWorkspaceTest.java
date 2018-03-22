package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderRefundWorkspaceTest;

/*退款订单*/
public class DepartmentOrderRefundWorkspaceTest extends SalesmanOrderRefundWorkspaceTest {
	@Before
	public void setup() {
		super.setup();
		urlList = "/crm/order/department/refund/list";
		resourceNodeCode = "Gsb_Supplier_Order_Department_Refund";
		//listPartServiceController = DepartmentOrderRefundListPart.class.getName();
		listFilter = "";
	}
}
