package com.gongsibao.panda.supplier.order;

import com.gongsibao.panda.supplier.order.workspace.audit.*;
import com.gongsibao.panda.supplier.order.workspace.department.*;
import com.gongsibao.panda.supplier.order.workspace.salesman.*;
import com.gongsibao.panda.supplier.reference.OrderDepartmentReferenceTest;
import com.gongsibao.panda.supplier.reference.OrderSalesmanReferenceTest;
import com.gongsibao.panda.supplier.reference.OrderSupplierReferenceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.ActionAllTest;
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
        SoCreatReceivedPerformanceWorkspaceTest.class,
        SalesmanOrderInvoiceWorkspaceTest.class,


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

        ActionAllTest.class,
        OrderNavigationTest.class,
})
public class OrderAllTest {

}
