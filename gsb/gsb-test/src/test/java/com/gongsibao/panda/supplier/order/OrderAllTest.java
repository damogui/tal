package com.gongsibao.panda.supplier.order;

import com.gongsibao.panda.supplier.order.workspace.salesman.*;
import com.gongsibao.panda.supplier.reference.SupplierU8BankReferenceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.order.action.ActionAllTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditCarryoverWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditPricingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.audit.AuditStagingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderAllWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderPerformanceWorspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderReceivedWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentOrderRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentReportDayWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentReportMonthWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.department.DepartmentReportWeekWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({
        OrderResourceTest.class,

        SupplierU8BankReferenceTest.class,

        //我的订单
        SalesmanOrderAddWorkspaceTest.class,
        SalesmanOrderAllWorkspaceTest.class,
        SalesmanOrderCarryoverWorkspaceTest.class,
        SalesmanOrderContractWorkspaceTest.class,
        SalesmanOrderPerformanceWorspaceTest.class,
        SalesmanOrderReceivedWorkspaceTest.class,
        SalesmanOrderRefundWorkspaceTest.class,
        SalesmanOrderStagingWorkspaceTest.class,
        SalesmanReportDayWorkspaceTest.class,
        SalesmanReportWeekWorkspaceTest.class,
        SalesmanReportMonthWorkspaceTest.class,
        SoCreatOrderPerformanceWorkspaceTest.class,
        SoCreatReceivedPerformanceWorkspaceTest.class,
        SalesmanOrderInvoiceWorkspaceTest.class,


        //部门订单
        DepartmentOrderAllWorkspaceTest.class,
        DepartmentOrderPerformanceWorspaceTest.class,
        DepartmentOrderReceivedWorkspaceTest.class,
        DepartmentOrderRefundWorkspaceTest.class,
        DepartmentReportDayWorkspaceTest.class,
        DepartmentReportWeekWorkspaceTest.class,
        DepartmentReportMonthWorkspaceTest.class,

        //订单审核
        AuditPricingWorkspaceTest.class,
        AuditCarryoverWorkspaceTest.class,
        AuditRefundWorkspaceTest.class,
        AuditStagingWorkspaceTest.class,

        ActionAllTest.class,
        OrderNavigationTest.class,
})
public class OrderAllTest {

}
