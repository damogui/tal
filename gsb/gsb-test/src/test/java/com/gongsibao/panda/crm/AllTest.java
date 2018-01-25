package com.gongsibao.panda.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

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
import com.gongsibao.panda.crm.workspace.TaskFollowUpWorkspaceTest;
import com.gongsibao.panda.crm.workspace.department.DepartAllTaskWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartAlreadySignWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartDefeatedWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartFollowIngWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartHighSeasWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartUnFoolowWorkspace;
import com.gongsibao.panda.crm.workspace.department.DepartUnStartWorkspace;
import com.gongsibao.panda.crm.workspace.my.AllTaskWorkspace;
import com.gongsibao.panda.crm.workspace.my.AlreadySignWorkspace;
import com.gongsibao.panda.crm.workspace.my.CheckAbnormalWorkspace;
import com.gongsibao.panda.crm.workspace.my.CustomerWorkspace;
import com.gongsibao.panda.crm.workspace.my.DefeatedWorkspace;
import com.gongsibao.panda.crm.workspace.my.FollowIngWorkspace;
import com.gongsibao.panda.crm.workspace.my.UnFoolowWorkspace;
import com.gongsibao.panda.crm.workspace.my.UnStartWorkspace;
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
				
		AllTaskWorkspace.class,
		UnStartWorkspace.class,
		UnFoolowWorkspace.class,
		FollowIngWorkspace.class,
		AlreadySignWorkspace.class,
		//DefeatedWorkspace.class,
		CheckAbnormalWorkspace.class,
		TaskFollowUpWorkspaceTest.class,
		CustomerWorkspace.class,
		
		
		DepartAllTaskWorkspace.class,
		DepartAlreadySignWorkspace.class,
		DepartDefeatedWorkspace.class,
		DepartFollowIngWorkspace.class,
		DepartUnFoolowWorkspace.class,
		DepartUnStartWorkspace.class,
		DepartHighSeasWorkspace.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}