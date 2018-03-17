package com.gongsibao.panda.platform.operation.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;

public class StagingWorkspaceTest extends SalesmanOrderStagingWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/staging/list";
		resourceNodeCode = "Operation_Order_Staging";
		listFilter = "";
	}

}
