package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;
import com.gongsibao.trade.web.SalesmanStagingListPart;
import com.gongsibao.trade.web.platform.StagingListPart;

public class StagingWorkspaceTest extends SalesmanOrderStagingWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/staging/list";
		resourceNodeCode = "Operation_Order_Staging";
		listFilter = "";
		listPartServiceController = StagingListPart.class.getName();
	}

}
