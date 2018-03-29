package com.gongsibao.panda.platform.trade;

import com.gongsibao.panda.platform.trade.workspace.audit.*;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditContractFormWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditInvoiceFormWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.trade.workspace.audit.StagingAuditWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.AllOrderWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.CarryoverWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ContractFormWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ContractWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.InvoiceFormWorKspace;
import com.gongsibao.panda.platform.trade.workspace.order.InvoiceWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.MyOrderWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.OrderALLWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.OrderOperationWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.OrderPoolWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.PerformanceWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ReceivedWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.RefundWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ReportDayWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ReportMonthWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.ReportWeekWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.order.StagingWorkspaceTest;
import com.gongsibao.panda.platform.trade.workspace.payment.PaymentLogWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

        ResourceTest.class,

        OrderOperationWorkspaceTest.class,

        //订单管理:全部订单,我的订单,我的订单明细,订单池
        AllOrderWorkspaceTest.class,
        MyOrderWorkspaceTest.class,
//	MyOrderDetailWorkspaceTest.class,
        OrderPoolWorkspaceTest.class,

        //订单操作
        OrderALLWorkspaceTest.class,
        OrderPoolWorkspaceTest.class,
        CarryoverWorkspaceTest.class,
        ContractWorkspaceTest.class,
        InvoiceWorkspaceTest.class,
        PerformanceWorkspaceTest.class,
        ReceivedWorkspaceTest.class,
        RefundWorkspaceTest.class,
        StagingWorkspaceTest.class,
        ReportDayWorkspaceTest.class,
        ReportWeekWorkspaceTest.class,
        ReportMonthWorkspaceTest.class,
    /*//订单操作:操作订单池,我负责的订单,订单审核,订单流量监控
	OperationOrderPoolWorkspaceTest.class,
	MyResponsibleOrderWorkspaceTest.class,
	OrderAuditWorkspaceTest.class,
	OrderMonitoringWorkspaceTest.class,*/

        //审核中心:改价审核,合同审核,发票审核,收款审核,分期审核,退单审核,商城充值审核
        CarryoverAuditWorkspaceTest.class,
        CostAuditWorkspaceTest.class,
        PerformanceAuditWorkspaceTest.class,
        ChangePriceAuditWorkspaceTest.class,
        ContractAuditWorkspaceTest.class,
        InvoiceAuditWorkspaceTest.class,
        ReceivedAuditWorkspaceTest.class,
        RefundAuditWorkspaceTest.class,
        StagingAuditWorkspaceTest.class,
        AuditContractFormWorkspaceTest.class,
        AuditInvoiceFormWorkspaceTest.class,


        //成本管理:成本管理,收款方管理,请款审核,请款审核,供应商发票审核
	/*CostManageWorkspaceTest.class,
	CashOutAuditWorkspaceTest.class,
	PayeeManageWorkspaceTest.class,
	SupplierInvoiceAuditWorkspaceTest.class,*/

        //支付记录:支付记录
        PaymentLogWorkspaceTest.class,

        //结算中心:分成管理,结算列表,打款列表
	/*CommissionManagerWorkspaceTest.class,
	SettleListWorkspaceTest.class,
	RemittanceListWorkspaceTest.class,*/

        ContractFormWorkspaceTest.class,
        InvoiceFormWorKspace.class,
        NavigationTest.class
})

public class AllTest {

}
