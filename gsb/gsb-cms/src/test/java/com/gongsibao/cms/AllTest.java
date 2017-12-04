package com.gongsibao.cms;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.cms.workspace.ProductWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		ProductWorkspaceTest.class,
		NavigationTest.class
		})
		

public class AllTest {

}
