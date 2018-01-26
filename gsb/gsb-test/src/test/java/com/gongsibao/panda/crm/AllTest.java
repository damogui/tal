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
import com.gongsibao.panda.crm.workspace.department.DepartmentAllTaskWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentAlreadySignWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentDefeatedWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentFollowIngWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentHighSeasWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentUnFoolowWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartmentUnStartWorkspace;
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
		
		//部门
		DepartmentAddCustomerWorkspaceTest.class,
		DepartmentCustomerEditWorkspaceTest.class,
		DepartmentTaskAddWorkspaceTest.class,
		DepartmentTaskEditWorkspaceTest.class,
		
		SalesmanVerifyCustomerWorkspaceTest.class,
		DepartmentAllTaskWorkspace.class,
		DepartmentAlreadySignWorkspace.class,
		DepartmentDefeatedWorkspace.class,
		DepartmentFollowIngWorkspace.class,
		DepartmentUnFoolowWorkspace.class,
		DepartmentUnStartWorkspace.class,
		DepartmentHighSeasWorkspace.class,
		
		//Action
		ActionAllTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}
