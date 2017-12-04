package com.gongsibao.trade;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.trade.workspace.SoOrderWorkspaceTest;




@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		SoOrderWorkspaceTest.class,
		NavigationTest.class
		})
public class AllTest {

}
