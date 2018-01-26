package com.gongsibao.panda.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.crm.action.ActionAllTest;
import com.gongsibao.panda.crm.reference.CityReferenceTest;
import com.gongsibao.panda.crm.reference.CompanyIntentionReferenceTest;
import com.gongsibao.panda.crm.reference.CustomerRefereneTest;
import com.gongsibao.panda.crm.reference.EmployeeRefereneTest;
import com.gongsibao.panda.crm.reference.NCustomerRefereneTest;
import com.gongsibao.panda.crm.reference.NProductReferenceTest;
import com.gongsibao.panda.crm.reference.ProductReferenceTest;
import com.gongsibao.panda.crm.reference.SupplierDepartmentRefereneTest;
import com.gongsibao.panda.crm.reference.SupplierRefereneTest;
import com.gongsibao.panda.crm.workspace.CompanyIntentionWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerAllWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerOrderWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerServiceConfigWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentAllTaskWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentSignWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentDefeatedWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentFollowIngWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentHighSeasWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentTimeOutWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentUnFoolowWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentUnStartWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartmentVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.form.DepartmentAddCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.form.DepartmentCustomerEditWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.form.DepartmentTaskAddWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.form.DepartmentTaskEditWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanAllTaskWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanAlreadySignWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanDefeatedWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanFollowIngWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanTimeOutWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanUnFoolowWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanUnStartWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanVerifyCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.SalesmanAddCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.SalesmanCustomerEditWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.SalesmanTaskAddWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.SalesmanTaskEditWorkspaceTest;
import com.gongsibao.panda.crm.workspace.sys.SysDepartmentWorkspaceTest;
import com.gongsibao.panda.crm.workspace.sys.SysSalesmanWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		CustomerRefereneTest.class,
		CityReferenceTest.class,
		ProductReferenceTest.class,
		CompanyIntentionReferenceTest.class,
		NCustomerRefereneTest.class,
		SupplierRefereneTest.class,
		SupplierDepartmentRefereneTest.class,
		EmployeeRefereneTest.class,
		NProductReferenceTest.class,
		
		
		CustomerAllWorkspaceTest.class,
		CompanyIntentionWorkspaceTest.class,
		//系统设置
		SysDepartmentWorkspaceTest.class,
		SysSalesmanWorkspaceTest.class,
		
		
//		CustomerMyWorkspaceTest.class,
//		EnterpriseWorkspaceTest.class,
//		CustomerPoolWorkspaceTest.class,
//		CustomerOpertionWorkspaceTest.class,
//		
		CustomerServiceConfigWorkspaceTest.class,
		CustomerOrderWorkspaceTest.class,
//		BaseServiceProviderWorkspaceTest.class,

		
		//业务员
		SalesmanAddCustomerWorkspaceTest.class,
		SalesmanCustomerEditWorkspaceTest.class,
		SalesmanTaskAddWorkspaceTest.class,
		SalesmanTaskEditWorkspaceTest.class,
		SalesmanAllCustomerWorkspaceTest.class,
		SalesmanAllTaskWorkspaceTest.class,
		SalesmanUnStartWorkspaceTest.class,
		SalesmanUnFoolowWorkspaceTest.class,
		SalesmanFollowIngWorkspaceTest.class,
		SalesmanAlreadySignWorkspaceTest.class,
		SalesmanDefeatedWorkspaceTest.class,
		SalesmanCheckAbnormalWorkspaceTest.class,
		SalesmanTimeOutWorkspaceTest.class,
		SalesmanVerifyCustomerWorkspaceTest.class,
		
		//部门
		DepartmentAddCustomerWorkspaceTest.class,
		DepartmentCustomerEditWorkspaceTest.class,
		DepartmentTaskAddWorkspaceTest.class,
		DepartmentTaskEditWorkspaceTest.class,
		
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
		
		//Action
		ActionAllTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}
