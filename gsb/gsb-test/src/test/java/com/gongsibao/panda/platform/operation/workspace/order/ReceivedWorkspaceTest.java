package com.gongsibao.panda.platform.operation.workspace.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import org.junit.Before;
import org.junit.Test;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;

public class ReceivedWorkspaceTest extends SalesmanOrderReceivedWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/received/list";
		resourceNodeCode = "Operation_Order_Received";
		listFilter = "";
	}

}
