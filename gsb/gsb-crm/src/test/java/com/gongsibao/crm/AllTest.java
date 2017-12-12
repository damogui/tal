package com.gongsibao.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.crm.reference.CityReferenceTest;
import com.gongsibao.crm.reference.CompanyIntentionReferenceTest;
import com.gongsibao.crm.reference.CustomerRefereneTest;
import com.gongsibao.crm.reference.ProductReferenceTest;
import com.gongsibao.crm.workspace.CompanyIntentionWorkspaceTest;
import com.gongsibao.crm.workspace.CustomerAllWorkspaceTest;
import com.gongsibao.crm.workspace.CustomerOrderWorkspaceTest;
import com.gongsibao.crm.workspace.CustomerServiceConfigWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		CustomerRefereneTest.class,
		CityReferenceTest.class,
		ProductReferenceTest.class,
		CompanyIntentionReferenceTest.class,
		
		
		CustomerAllWorkspaceTest.class,
//		CustomerMyWorkspaceTest.class,
//		EnterpriseWorkspaceTest.class,
//		CustomerPoolWorkspaceTest.class,
//		CustomerOpertionWorkspaceTest.class,
//		
		CustomerServiceConfigWorkspaceTest.class,
		CustomerOrderWorkspaceTest.class,
		CompanyIntentionWorkspaceTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}