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
import com.gongsibao.panda.crm.workspace.department.AddDepartmentCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartAllTaskWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartAlreadySignWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartDefeatedWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartFollowIngWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartHighSeasWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartUnFoolowWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartUnStartWorkspace;
import com.gongsibao.panda.crm.workspace.salesman.AllSalesmanCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.AllSalesmanTaskWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.AlreadySignSalesmanWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.CheckAbnormalSalesmanWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.DefeatedSalesmanWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.FollowIngSalesmanWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.UnFoolowSalesmanWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.UnStartSalesmanWorkspace;
import com.gongsibao.panda.crm.workspace.salesman.VerifySalesmanCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.MyAddCustomerWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.MyCustomerEditWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.MyTaskAddWorkspaceTest;
import com.gongsibao.panda.crm.workspace.salesman.form.MyTaskEditWorkspaceTest;
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

		
		MyAddCustomerWorkspaceTest.class,
		MyCustomerEditWorkspaceTest.class,
		MyTaskAddWorkspaceTest.class,
		MyTaskEditWorkspaceTest.class,
		

		AllSalesmanCustomerWorkspaceTest.class,
		AllSalesmanTaskWorkspaceTest.class,
		
		UnStartSalesmanWorkspace.class,
		UnFoolowSalesmanWorkspaceTest.class,
		FollowIngSalesmanWorkspaceTest.class,
		AlreadySignSalesmanWorkspaceTest.class,
		DefeatedSalesmanWorkspaceTest.class,
		CheckAbnormalSalesmanWorkspaceTest.class,
		
		AddDepartmentCustomerWorkspaceTest.class,
		VerifySalesmanCustomerWorkspaceTest.class,
		DepartAllTaskWorkspace.class,
		DepartAlreadySignWorkspace.class,
		DepartDefeatedWorkspace.class,
		DepartFollowIngWorkspace.class,
		DepartUnFoolowWorkspace.class,
		DepartUnStartWorkspace.class,
		DepartHighSeasWorkspace.class,
		
		ActionAllTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}
