package com.gongsibao.panda.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.crm.reference.CityReferenceTest;
import com.gongsibao.panda.crm.reference.CompanyIntentionReferenceTest;
import com.gongsibao.panda.crm.reference.CustomerRefereneTest;
import com.gongsibao.panda.crm.reference.ProductReferenceTest;
import com.gongsibao.panda.crm.workspace.CompanyIntentionWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerAllWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerOrderWorkspaceTest;
import com.gongsibao.panda.crm.workspace.CustomerServiceConfigWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		CustomerRefereneTest.class,
		CityReferenceTest.class,
		ProductReferenceTest.class,
		CompanyIntentionReferenceTest.class,
		
		
		CustomerAllWorkspaceTest.class,
		CompanyIntentionWorkspaceTest.class,
		
//		CustomerMyWorkspaceTest.class,
//		EnterpriseWorkspaceTest.class,
//		CustomerPoolWorkspaceTest.class,
//		CustomerOpertionWorkspaceTest.class,
//		
		CustomerServiceConfigWorkspaceTest.class,
		CustomerOrderWorkspaceTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}