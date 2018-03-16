package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class StagingWorkspaceTest extends SalesmanOrderStagingWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/staging/list";
		resourceNodeCode = "Operation_Order_Staging";
		listFilter = "";
	}

}
