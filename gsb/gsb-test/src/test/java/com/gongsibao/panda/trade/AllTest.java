package com.gongsibao.panda.trade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.trade.workspace.order.OrderOperationWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	OrderOperationWorkspaceTest.class,
	
	//订单管理:全部订单,我的订单,我的订单明细,订单池
	/*AllOrderWorkspaceTest.class,
	MyOrderWorkspaceTest.class,
	MyOrderDetailWorkspaceTest.class,
	OrderPoolWorkspaceTest.class,
	
	//订单操作:操作订单池,我负责的订单,订单审核,订单流量监控
	OperationOrderPoolWorkspaceTest.class,
	MyResponsibleOrderWorkspaceTest.class,
	OrderAuditWorkspaceTest.class,
	OrderMonitoringWorkspaceTest.class,

	//审核中心:改价审核,合同审核,发票审核,收款审核,分期审核,退单审核,商城充值审核
	ChangePriceAuditWorkspaceTest.class,
	ContractAuditWorkspaceTest.class,
	InstallmentWorkspaceTest.class,
	InvoiceAuditWorkspaceTest.class,
	ReceiptAuditWorkspaceTest.class,
	RechargeWorkspaceTest.class,
	RefundAuditWorkspaceTest.class,
	
	
	//成本管理:成本管理,收款方管理,请款审核,请款审核,供应商发票审核
	CostManageWorkspaceTest.class,
	CashOutAuditWorkspaceTest.class,
	PayeeManageWorkspaceTest.class,
	SupplierInvoiceAuditWorkspaceTest.class,
	
	//支付记录:支付记录
	PaymentLogWorkspaceTest.class,
	
	//结算中心:分成管理,结算列表,打款列表
	CommissionManagerWorkspaceTest.class,
	SettleListWorkspaceTest.class,
	RemittanceListWorkspaceTest.class,*/

	
	NavigationTest.class
})

public class AllTest {

}
