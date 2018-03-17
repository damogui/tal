package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;

public class ReceivedWorkspaceTest extends SalesmanOrderReceivedWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/received/list";
		resourceNodeCode = "Operation_Order_Received";
		listFilter = "";
	}

}
