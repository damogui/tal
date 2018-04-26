package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderRefundWorkspaceTest;
import com.gongsibao.trade.web.SalesmanOrderReceivedListPart;
import com.gongsibao.trade.web.platform.RefundListPart;

public class RefundWorkspaceTest extends SalesmanOrderRefundWorkspaceTest {
	@Before
	public void setup() {

		super.setup();
		urlList = "/operation/order/refund/list";
		resourceNodeCode = "Operation_Order_Refund";
		listFilter = "";
		listPartServiceController = RefundListPart.class.getName ();
	}

}
