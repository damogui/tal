package com.gongsibao.panda.supplier.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.operation.workspace.crm.old.CompanyIntentionWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.old.CustomerAllWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.old.CustomerMyOrderWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.old.CustomerOrderWorkspaceTest;
import com.gongsibao.panda.platform.operation.workspace.crm.old.CustomerServiceConfigWorkspaceTest;
import com.gongsibao.panda.supplier.crm.action.ActionAllTest;
import com.gongsibao.panda.supplier.crm.reference.CityReferenceTest;
import com.gongsibao.panda.supplier.crm.reference.CompanyIntentionReferenceTest;
import com.gongsibao.panda.supplier.crm.reference.CustomerRefereneTest;
import com.gongsibao.panda.supplier.crm.reference.EmployeeRefereneTest;
import com.gongsibao.panda.supplier.crm.reference.ProductReferenceTest;
import com.gongsibao.panda.supplier.crm.reference.SupplierDepartmentRefereneTest;
import com.gongsibao.panda.supplier.crm.reference.SupplierRefereneTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentAllCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentAllTaskWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentDefeatedWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentFollowIngWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentHighSeasWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentSignWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentTaskFollowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentTimeOutWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentUnFoolowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentUnStartWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.DepartmentVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.form.DepartmentCustomerAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.form.DepartmentCustomerEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.form.DepartmentTaskAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.form.DepartmentTaskEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.department.form.DepartmentTaskFollowFormWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.report.ComprehenStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.report.FollowStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.report.FunnelStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanAllCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanDefeatedWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanFollowIngWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanSignWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanTaskFollowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanTimeOutWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanUnFoolowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanUnStartWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.SalesmanVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.form.SalesmanCustomerAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.form.SalesmanCustomerEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.form.SalesmanTaskAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.form.SalesmanTaskEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.workspace.salesman.form.SalesmanTaskFollowFormWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({
		CrmResourceTest.class,
		CustomerRefereneTest.class,
		CityReferenceTest.class,
		ProductReferenceTest.class,
		CompanyIntentionReferenceTest.class,
		SupplierRefereneTest.class,
		SupplierDepartmentRefereneTest.class,
		EmployeeRefereneTest.class,

		CustomerAllWorkspaceTest.class,
		CustomerMyOrderWorkspaceTest.class,
		CompanyIntentionWorkspaceTest.class,
		CustomerServiceConfigWorkspaceTest.class,
		CustomerOrderWorkspaceTest.class,

		// 业务员
		SalesmanCustomerAddWorkspaceTest.class, 
		SalesmanCustomerEditWorkspaceTest.class, 
		SalesmanTaskAddWorkspaceTest.class, 
		SalesmanTaskEditWorkspaceTest.class,
		SalesmanTaskFollowFormWorkspaceTest.class,

		SalesmanAllCustomerWorkspaceTest.class, 
		SalesmanAllTaskWorkspaceTest.class, 
		SalesmanUnStartWorkspaceTest.class, 
		SalesmanUnFoolowWorkspaceTest.class, 
		SalesmanFollowIngWorkspaceTest.class,
		SalesmanSignWorkspaceTest.class, 
		SalesmanDefeatedWorkspaceTest.class, 
		SalesmanCheckAbnormalWorkspaceTest.class,
		SalesmanTimeOutWorkspaceTest.class,
		SalesmanVerifyCustomerWorkspaceTest.class,
		DepartmentTaskFollowWorkspaceTest.class,
		
		// 部门
		DepartmentCustomerAddWorkspaceTest.class, 
		DepartmentCustomerEditWorkspaceTest.class, 
		DepartmentTaskAddWorkspaceTest.class, 
		DepartmentTaskEditWorkspaceTest.class,
		DepartmentTaskFollowFormWorkspaceTest.class,

		DepartmentAllCustomerWorkspaceTest.class, 
		DepartmentAllTaskWorkspaceTest.class, 
		DepartmentCheckAbnormalWorkspaceTest.class, 
		DepartmentDefeatedWorkspaceTest.class,
		DepartmentFollowIngWorkspaceTest.class, 
		DepartmentHighSeasWorkspaceTest.class, 
		DepartmentSignWorkspaceTest.class, 
		DepartmentTimeOutWorkspaceTest.class, 
		DepartmentUnFoolowWorkspaceTest.class,
		DepartmentUnStartWorkspaceTest.class, 
		DepartmentVerifyCustomerWorkspaceTest.class,

		ComprehenStatisticalWorkspaceTest.class,
		/* CustomerServiceStatisticalWorkspaceTest.class, */
		FollowStatisticalWorkspaceTest.class, 
		FunnelStatisticalWorkspaceTest.class,
		SalesmanTaskFollowWorkspaceTest.class,

		// Action
		ActionAllTest.class,

		CrmNavigationTest.class })
public class CrmAllTest {

}
