package com.gongsibao.trade;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.trade.SoOrder;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "订单查询";
		this.treePath = "panda/gsb/trade";
		this.resourceNode = "GSB_TRADE";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_TRADE", "订单查询", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_TRADE_Enquiry", "订单查询", "", 1);
		{
			createPTreeNode(tree, "GSB_TRADE_Enquiry", null, "TRADE_" + SoOrder.class.getSimpleName(), "订单查询列表", "/trade/order/list", 1);
		}
		
		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_TRADE_Order", "订单管理", "", 2);
		{
			createPTreeNode(tree, "GSB_TRADE_Enquiry", null, "TRADE_" + SoOrder.class.getSimpleName(), "订单查询列表", "/trade/order/list", 1);
		}
	}
}
