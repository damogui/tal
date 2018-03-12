package com.gongsibao.panda.platform.bd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.bd.workspace.behavior.UserBehaviorWorkSpaceTest;


@RunWith(Suite.class)
@SuiteClasses({
	
	ResourceTest.class,
	UserBehaviorWorkSpaceTest.class,
	NavigationTest.class
})

public class AllTest {
}
