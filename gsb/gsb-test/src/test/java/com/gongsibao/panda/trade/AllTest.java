package com.gongsibao.panda.trade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.trade.workspace.audit.ChangePriceAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.ContractAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.InstallmentWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.InvoiceAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.ReceiptAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.RechargeWorkspaceTest;
import com.gongsibao.panda.trade.workspace.audit.RefundAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.cost.CashOutAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.cost.CostManageWorkspaceTest;
import com.gongsibao.panda.trade.workspace.cost.PayeeManageWorkspaceTest;
import com.gongsibao.panda.trade.workspace.cost.SupplierInvoiceAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.operation.MyResponsibleOrderWorkspaceTest;
import com.gongsibao.panda.trade.workspace.operation.OperationOrderPoolWorkspaceTest;
import com.gongsibao.panda.trade.workspace.operation.OrderAuditWorkspaceTest;
import com.gongsibao.panda.trade.workspace.operation.OrderMonitoringWorkspaceTest;
import com.gongsibao.panda.trade.workspace.order.AllOrderWorkspaceTest;
import com.gongsibao.panda.trade.workspace.order.MyOrderDetailWorkspaceTest;
import com.gongsibao.panda.trade.workspace.order.MyOrderWorkspaceTest;
import com.gongsibao.panda.trade.workspace.order.OrderPoolWorkspaceTest;
import com.gongsibao.panda.trade.workspace.payment.PaymentLogWorkspaceTest;
import com.gongsibao.panda.trade.workspace.settle.CommissionManagerWorkspaceTest;
import com.gongsibao.panda.trade.workspace.settle.RemittanceListWorkspaceTest;
import com.gongsibao.panda.trade.workspace.settle.SettleListWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//订单管理:全部订单,我的订单,我的订单明细,订单池
	AllOrderWorkspaceTest.class,
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
	RemittanceListWorkspaceTest.class,

	
	NavigationTest.class
})

public class AllTest {

}
