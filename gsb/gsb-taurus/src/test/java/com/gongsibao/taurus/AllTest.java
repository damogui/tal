package com.gongsibao.taurus;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.taurus.workspace.UserWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 

		UserWorkspaceTest.class,
		
		
		NavigationTest.class
		})
		
public class AllTest {

}