package com.gongsibao.panda.supplier.order;

import com.gongsibao.panda.supplier.order.workspace.interactive.InteractiveOperatGroupQueryWorkspaceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.workspace.audit.AuditCarryoverWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditContractWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditInvoiceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditOrderPerformanceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditOrderWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditPayPerformanceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditPayWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditPricingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditSalesmanOrderContractFormWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditSalesmanOrderInvoiceFormWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditStagingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderAllWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderCarryoverWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderContractWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderDetailWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderInvoiceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderPayWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderPerformanceWorspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderReceivedWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderStagingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.interactive.InteractiveMyInChargeWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.interactive.InteractiveOperationPoolWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAddWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderCarryoverWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractFormWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderDelWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderDetailWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceFormWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderInvoiceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPayWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SoCreatOrderPerformanceWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.settle.MySettleWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.settle.UnSettleWorkspaceTest;
import com.gongsibao.panda.supplier.reference.OrderDepartmentReferenceTest;
import com.gongsibao.panda.supplier.reference.OrderSalesmanReferenceTest;
import com.gongsibao.panda.supplier.reference.OrderSupplierReferenceTest;
import com.gongsibao.panda.supplier.reference.SupplierU8BankReferenceTest;

@RunWith(Suite.class)
@SuiteClasses({
        OrderResourceTest.class,
        /*反射表的扩展*/
        OrderSupplierReferenceTest.class,
        OrderDepartmentReferenceTest.class,
        OrderSalesmanReferenceTest.class,
        SupplierU8BankReferenceTest.class,

        //我的订单
        SalesmanOrderAddWorkspaceTest.class,
        SalesmanOrderAllWorkspaceTest.class,
        SalesmanOrderCarryoverWorkspaceTest.class,
        SalesmanOrderContractWorkspaceTest.class,
        SalesmanOrderPerformanceWorspaceTest.class,
        SalesmanOrderPayWorkspaceTest.class,
        SalesmanOrderReceivedWorkspaceTest.class,
        SalesmanOrderRefundWorkspaceTest.class,
        SalesmanOrderStagingWorkspaceTest.class,
//        SalesmanReportDayWorkspaceTest.class,
//        SalesmanReportWeekWorkspaceTest.class,
//        SalesmanReportMonthWorkspaceTest.class,
        SoCreatOrderPerformanceWorkspaceTest.class,
        SalesmanOrderInvoiceWorkspaceTest.class,
        SalesmanOrderDetailWorkspaceTest.class,
        SalesmanOrderContractFormWorkspaceTest.class,
        SalesmanOrderInvoiceFormWorkspaceTest.class,
        SalesmanOrderDelWorkspaceTest.class,//无效订单

        //部门订单
        DepartmentOrderAllWorkspaceTest.class,
        DepartmentOrderPerformanceWorspaceTest.class,
        DepartmentOrderPayWorkspaceTest.class,
        DepartmentOrderReceivedWorkspaceTest.class,
        DepartmentOrderRefundWorkspaceTest.class,
        DepartmentOrderCarryoverWorkspaceTest.class,
        DepartmentOrderContractWorkspaceTest.class,
        DepartmentOrderInvoiceWorkspaceTest.class,
        DepartmentOrderStagingWorkspaceTest.class,
        DepartmentOrderDetailWorkspaceTest.class,
//        DepartmentReportDayWorkspaceTest.class,
//        DepartmentReportWeekWorkspaceTest.class,
//        DepartmentReportMonthWorkspaceTest.class,

        //订单审核
        AuditOrderWorkspaceTest.class,
        AuditOrderPerformanceWorkspaceTest.class,
        AuditPayPerformanceWorkspaceTest.class,
        AuditPayWorkspaceTest.class,
        AuditPricingWorkspaceTest.class,
        AuditCarryoverWorkspaceTest.class,
        AuditRefundWorkspaceTest.class,
        AuditStagingWorkspaceTest.class,
        AuditContractWorkspaceTest.class,
        AuditInvoiceWorkspaceTest.class,
        AuditSalesmanOrderContractFormWorkspaceTest.class,
        AuditSalesmanOrderInvoiceFormWorkspaceTest.class,

        // 结算
        UnSettleWorkspaceTest.class,
        MySettleWorkspaceTest.class,

        //交互管理
        InteractiveMyInChargeWorkspaceTest.class,
        InteractiveOperationPoolWorkspaceTest.class,
        InteractiveOperatGroupQueryWorkspaceTest.class,

        OrderNavigationTest.class,
})
public class OrderAllTest {

}
