package com.gongsibao.panda.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.cms.workspace.ProductWorkspaceTest;
import com.gongsibao.panda.operation.workspace.cms.ProductPackageWorkspaceTest;
import com.gongsibao.panda.operation.workspace.supplier.CPAuditWorkspaceTest;
import com.gongsibao.panda.operation.workspace.supplier.SPAuditWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.ActiveUserWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.DayStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.NewUserPerDayWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserConsStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserRenewalStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserWorkspaceTest;


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
	ProductWorkspaceTest.class,
	
	//CMS
	ProductPackageWorkspaceTest.class,
	ProductWorkspaceTest.class,
	
	//钉钉
	
	//供应商
	CPAuditWorkspaceTest.class,
	SPAuditWorkspaceTest.class,
	
	
	NavigationTest.class
	
})

public class AllTest {

}

