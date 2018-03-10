package com.gongsibao.panda.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.crm.action.ActionAllTest;
import com.gongsibao.panda.crm.reference.CityReferenceTest;
import com.gongsibao.panda.crm.reference.CompanyIntentionReferenceTest;
import com.gongsibao.panda.crm.reference.CustomerRefereneTest;
import com.gongsibao.panda.crm.reference.EmployeeRefereneTest;
import com.gongsibao.panda.crm.reference.ProductReferenceTest;
import com.gongsibao.panda.crm.reference.SupplierDepartmentRefereneTest;
import com.gongsibao.panda.crm.reference.SupplierRefereneTest;
import com.gongsibao.panda.crm.workspace.CompanyIntentionWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerAllWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerMyOrderWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerOrderWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerServiceConfigWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentAllCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentAllTaskWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentDefeatedWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentFollowIngWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentHighSeasWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentSignWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentTimeOutWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentUnFoolowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentUnStartWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.DepartmentVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.form.DepartmentCustomerAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.form.DepartmentCustomerEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.form.DepartmentTaskAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.form.DepartmentTaskEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.department.form.DepartmentTaskFollowFormWorkspaceTest;
import com.gongsibao.panda.supplier.crm.report.ComprehenStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.report.FollowStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.report.FunnelStatisticalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanAllCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanAllTaskWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanDefeatedWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanFollowIngWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanSignWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanTimeOutWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanUnFoolowWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanUnStartWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.SalesmanVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.form.SalesmanCustomerAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.form.SalesmanCustomerEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.form.SalesmanTaskAddWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.form.SalesmanTaskEditWorkspaceTest;
import com.gongsibao.panda.supplier.crm.salesman.form.SalesmanTaskFollowFormWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
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

		
		
//		CustomerMyWorkspaceTest.class,
//		EnterpriseWorkspaceTest.class,
//		CustomerPoolWorkspaceTest.class,
//		CustomerOpertionWorkspaceTest.class,
//		
		CustomerServiceConfigWorkspaceTest.class,
		CustomerOrderWorkspaceTest.class,
//		BaseServiceProviderWorkspaceTest.class,

		
		//业务员
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
		
		//部门
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
		/*CustomerServiceStatisticalWorkspaceTest.class,*/
		FollowStatisticalWorkspaceTest.class,
		FunnelStatisticalWorkspaceTest.class,
		
		
		//Action
		ActionAllTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}
