package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderRefundWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class RefundWorkspaceTest extends SalesmanOrderRefundWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/refund/list";
		resourceNodeCode = "Operation_Order_Refund";
		listFilter = "";
	}

}
