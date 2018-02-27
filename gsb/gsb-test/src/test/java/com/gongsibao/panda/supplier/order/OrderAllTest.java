package com.gongsibao.panda.supplier.order;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAddWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderAllWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderCarryoverWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderContractWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderPerformanceWorspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderReceivedWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderRefundWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderStagingWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanReportDayWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanReportMonthWorkspaceTest;
import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanReportWeekWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	OrderResourceTest.class,
	
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
	
	
	DepartmentOrderAllWorkspaceTest.class,
	DepartmentOrderPerformanceWorspaceTest.class,
	DepartmentOrderReceivedWorkspaceTest.class,
	DepartmentOrderRefundWorkspaceTest.class,
	DepartmentReportDayWorkspaceTest.class,
	DepartmentReportWeekWorkspaceTest.class,
	DepartmentReportMonthWorkspaceTest.class,
	
	
	AuditPricingWorkspaceTest.class,
	AuditCarryoverWorkspaceTest.class,
	AuditRefundWorkspaceTest.class,
	AuditStagingWorkspaceTest.class,

	OrderNavigationTest.class, 
})
public class OrderAllTest {

}
