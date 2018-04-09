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

		String nodeIcon="fa fa-circle-o";
		createPTreeNode(tree, null, "fa fa-file-text-o", "Gsb_Supplier_Order", "订单管理", "", 9);
		{
			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Salesman", "我的订单", "", 1);
			{
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Add", "创建订单", "/crm/order/salesman/add", 1);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_All", "全部订单", "/crm/order/salesman/all/list", 2);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Performance", "订单业绩", "/crm/order/salesman/performance/list", 3);
                createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Pay", "我的回款", "/crm/order/salesman/pay/list", 4);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Received", "我的回款业绩", "/crm/order/salesman/received/list", 4);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Refund", "我的退款", "/crm/order/salesman/refund/list", 5);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Staging", "我的分期", "/crm/order/salesman/staging/list", 6);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Carryover", "我的结转", "/crm/order/salesman/carryover/list", 7);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Contract", "合同管理", "/crm/order/salesman/contract/list", 8);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Invoice", "发票管理", "/crm/order/salesman/invoice/list", 9);
				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", nodeIcon, "Gsb_Supplier_Order_Salesman_Detail", "我的明细订单", "/crm/order/salesman/detail/list", 10);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Day_Report", "日统计", "/crm/order/salesman/day/report", 10);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Week_Report", "周统计", "/crm/order/salesman/week/report", 11);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Salesman", null, "Gsb_Supplier_Order_Salesman_Month_Report", "月统计", "/crm/order/salesman/month/report", 12);
			}

			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Department", "部门订单", "", 2);
			{
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_All", "全部订单", "/crm/order/department/all/list", 1);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Performance", "订单业绩", "/crm/order/department/performance/list", 2);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Pay", "部门回款", "/crm/order/department/pay/list", 3);
                createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Received", "部门回款业绩", "/crm/order/department/received/list", 3);


                createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Refund", "退款订单", "/crm/order/department/refund/list", 4);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Staging", "分期订单", "/crm/order/department/staging/list", 5);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Carryover", "结转订单", "/crm/order/department/carryover/list", 6);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Contract", "合同管理", "/crm/order/department/contract/list", 7);
				createPTreeNode(tree, "Gsb_Supplier_Order_Department", nodeIcon, "Gsb_Supplier_Order_Department_Invoice", "发票管理", "/crm/order/department/invoice/list", 8);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Day_Report", "日统计", "/crm/order/department/day/report", 5);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Week_Report", "周统计", "/crm/order/department/week/report", 6);
//				createPTreeNode(tree, "Gsb_Supplier_Order_Department", null, "Gsb_Supplier_Order_Department_Month_Report", "月统计", "/crm/order/department/month/report", 7);
			}
			
			createPTreeNode(tree, "Gsb_Supplier_Order", null, "Gsb_Supplier_Order_Audit", "订单审核", "", 3);
			{


                createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Order", "订单审核", "/crm/order/audit/ortder/list", 1);

                createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Performance", "订单业绩审核", "/crm/order/audit/orderp/list", 2);
                createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Pay_Audit", "回款审核", "/crm/order/audit/pay/list", 3);
                createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Pay_Audit_Performance", "回款业绩审核", "/crm/order/audit/payper/list", 4);


				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Pricing", "定价审核", "/crm/order/audit/pricing/list", 5);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Refund", "退款审核", "/crm/order/audit/refund/list", 6);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Staging", "分期审核", "/crm/order/audit/staging/list", 7);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Carryover", "结转审核", "/crm/order/audit/carryover/list", 8);

				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Contract", "合同审核", "/crm/order/audit/contract/list", 9);
				createPTreeNode(tree, "Gsb_Supplier_Order_Audit", nodeIcon, "Gsb_Supplier_Order_Audit_Invoice", "发票审核", "/crm/order/audit/invoice/list", 10);
			}
		}
	}
}
