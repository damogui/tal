package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderDetailWorkspaceTest;

/**   
 * @ClassName:  MyOrderDetailWorkspaceTest   
 * @Description:TODO 我的订单明细
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:11:41   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderDetailWorkspaceTest  extends SalesmanOrderDetailWorkspaceTest{
	@Before
	public void setup() {
		super.setup();
		urlList = "/operation/order/detail/list";		
		resourceNodeCode = "Operation_Order_OrderProd";
		listFilter = "";
	}
}
