package com.gongsibao.panda.operation.taurus;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.taurus.workspace.ActiveUserWorkspaceTest;
import com.gongsibao.taurus.workspace.DayStatisticWorkspaceTest;
import com.gongsibao.taurus.workspace.NewUserPerDayWorkspaceTest;
import com.gongsibao.taurus.workspace.UserConsStatisticWorkspaceTest;
import com.gongsibao.taurus.workspace.UserRenewalStatisticWorkspaceTest;
import com.gongsibao.taurus.workspace.UserWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 

		UserWorkspaceTest.class,
		NewUserPerDayWorkspaceTest.class,
		UserConsStatisticWorkspaceTest.class,
		UserRenewalStatisticWorkspaceTest.class,
		ActiveUserWorkspaceTest.class,
		DayStatisticWorkspaceTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}