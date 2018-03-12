package com.gongsibao.panda.platform.trade;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;


public class NavigationTest extends NavigationBase {

	// 初始化导航信息
	@Before
	public void setup() {
		this.treeName = "交易中心";
		this.treePath = "panda/gsb/trade";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "交易中心", "fa fa-recycle fa-fw", 1);
	}

	// 创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_Manage";
		createPTreeNode(tree, null, "fa fa-file-text-o fa-fw", parentNodeCode, "订单管理", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_All_Order", "全部订单", "/trade/manage/order/all/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_My_Order", "我的订单", "/trade/manage/order/my/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_My_Order_Detail", "我的订单明细", "/trade/manage/orderdetail/my/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Order_Pool", "订单明细", "/trade/manage/order/pool/list", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Order_Operation", "订单操作", "/trade/manage/order/operation/list", 5);
		}

		/*parentNodeCode = ResourceTest.resourcePrefix + "_Operation";
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", parentNodeCode, "订单操作", "", 2);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Operation_Order", "操作订单池", "/trade/operation/order/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Responsible_Order", "我负责的订单", "/trade/operation/responsible/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Order_Audit", "订单审核", "/trade/operation/order/audit/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Monitoring_Pool", "订单流量监控", "/trade/operation/monitoring/pool/list", 4);
		}*/
		
		parentNodeCode = ResourceTest.resourcePrefix + "_Audit";
		createPTreeNode(tree, null, "fa fa-check-square-o fa-fw", parentNodeCode, "审核中心", "", 3);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Performance", "业绩审核", "/trade/audit/performance/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Received", "回款审核", "/trade/audit/received/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Refund", "退款审核", "/trade/audit/refund/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Carryover", "结转审核", "/trade/audit/carryover/list", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Invoice", "发票审核", "/trade/audit/invoice/list", 5);	
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Cost", "成本审核", "/trade/audit/cost/list", 6);		
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Contract", "合同审核", "/trade/audit/contract/list", 7);		
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Price_Change", "改价审核", "/trade/audit/change/list", 8);		
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Staging", "分期审核", "/trade/audit/staging/list", 9);
		}

		/*parentNodeCode = ResourceTest.resourcePrefix + "_Cost";
		createPTreeNode(tree, null, "fa fa-rmb fa-fw", parentNodeCode, "成本管理", "", 4);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Manage", "成本管理", "/trade/cost/manage/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Payee", "收款方管理", "/trade/cost/payee/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_CashOut", "请款审核", "/trade/cost/cashout/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Invoice", "供应商发票审核", "/trade/cost/invoice/list", 4);
		}
*/
		/*parentNodeCode = ResourceTest.resourcePrefix + "_Payment";
		createPTreeNode(tree, null, "fa fa-credit-card fa-fw", parentNodeCode, "支付记录", "", 5);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Payment_Log", "支付记录", "/trade/payment/log/list", 1);
		}*/

		/*parentNodeCode = ResourceTest.resourcePrefix + "_Settle";
		createPTreeNode(tree, null, "fa fa-calculator fa-fw", parentNodeCode, "结算中心", "", 6);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Commission", "分成管理", "/trade/settle/commission/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Settle", "结算列表", "/trade/settle/settle/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Remittance", "打款列表", "/trade/settle/remittance/list", 3);
		}*/

	}
}