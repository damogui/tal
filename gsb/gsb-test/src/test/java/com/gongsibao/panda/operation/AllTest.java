package com.gongsibao.panda.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.operation.workspace.taurus.ActiveUserWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.DayStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.NewUserPerDayWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserConsStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserRenewalStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserWorkspaceTest;
import com.gongsibao.panda.operation.workspace.wanda.WanDaProductWorkspaceTest;
import com.gongsibao.panda.operation.workspace.wanda.WanDaSoOrderWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//金牛座
	UserWorkspaceTest.class,
	NewUserPerDayWorkspaceTest.class,
	UserConsStatisticWorkspaceTest.class,
	UserRenewalStatisticWorkspaceTest.class,
	ActiveUserWorkspaceTest.class,
	DayStatisticWorkspaceTest.class,
	
	//万达
	WanDaProductWorkspaceTest.class,
	WanDaSoOrderWorkspaceTest.class,
	
	//CMS
//	ProductPackageWorkspaceTest.class,
//	ProductWorkspaceTest.class,
	
	//钉钉
	
	//供应商
//	CPAuditWorkspaceTest.class,
//	SPAuditWorkspaceTest.class,
	
	
	NavigationTest.class
	
})

public class AllTest {

}

