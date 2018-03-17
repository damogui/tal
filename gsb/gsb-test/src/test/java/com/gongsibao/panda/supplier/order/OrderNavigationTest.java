package com.gongsibao.panda.supplier.order;

import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;

public class OrderNavigationTest extends NavigationBase {

	public void createAccodions() {

	}

	protected void createPTree() {

		PNavigation tree = treeService.byPath("panda/gsb/supplier");
		this.doCreateTree(tree);
		grantPermission(tree);
		treeService.save(tree);
	}

	@Override
	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode, String icon, String code, String name, String url, int seq) {

		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setCode(code);
			node.setName(name);
			node.setUrl(url);
			node.setParent(parentCode);
			node.setSeq(seq);
			node.setIcon(icon);
			node.setPathId(tree.getId());
			tree.getTreeNodes().add(node);
		}

		return node;
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "Gsb_Supplier_Order", "订单管理", "", 9);
		{
			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Salesman", "我的订单", "", 1);
			{
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Add", "创建订单", "/crm/order/salesman/add", 1);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_All", "全部订单", "/crm/order/salesman/all/list", 2);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Performance", "订单业绩", "/crm/order/salesman/performance/list", 3);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Received", "回款业绩", "/crm/order/salesman/received/list", 4);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Refund", "退款订单", "/crm/order/salesman/refund/list", 5);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Staging", "分期订单", "/crm/order/salesman/staging/list", 6);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Carryover", "结转订单", "/crm/order/salesman/carryover/list", 7);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Contract", "合同管理", "/crm/order/salesman/contract/list", 8);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Invoice", "发票管理", "/crm/order/salesman/invoice/list", 9);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Day_Report", "日统计", "/crm/order/salesman/day/report", 10);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Week_Report", "周统计", "/crm/order/salesman/week/report", 11);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Month_Report", "月统计", "/crm/order/salesman/month/report", 12);
			}

			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Department", "部门订单", "", 2);
			{
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_All", "全部订单", "/crm/order/department/all/list", 1);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Performance", "订单业绩", "/crm/order/department/performance/list", 2);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Received", "回款业绩", "/crm/order/department/received/list", 3);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Refund", "退款订单", "/crm/order/department/refund/list", 4);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Day_Report", "日统计", "/crm/order/department/day/report", 5);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Week_Report", "周统计", "/crm/order/department/week/report", 6);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Month_Report", "月统计", "/crm/order/department/month/report", 7);
			}
			
			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Audit", "订单审核", "", 3);
			{
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", null, "Gsb_Supplier_Order_Audit_Pricing", "定价审核", "/crm/order/audit/pricing/list", 1);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", null, "Gsb_Supplier_Order_Audit_Refund", "退款审核", "/crm/order/audit/refund/list", 2);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", null, "Gsb_Supplier_Order_Audit_Staging", "分期审核", "/crm/order/audit/staging/list", 3);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", null, "Gsb_Supplier_Order_Audit_Carryover", "结转审核", "/crm/order/audit/carryover/list", 4);
			}
		}
	}
}
