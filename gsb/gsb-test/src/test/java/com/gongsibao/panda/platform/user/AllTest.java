package com.gongsibao.panda.platform.user;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.user.reference.OrganizationReferenceTest;
import com.gongsibao.panda.platform.user.reference.RoleReferenceTest;
import com.gongsibao.panda.platform.user.reference.UserReferenceTest;
import com.gongsibao.panda.platform.user.workspace.user.AuthWorkspaceTest;
import com.gongsibao.panda.platform.user.workspace.user.LoginLogWorkspaceTest;
import com.gongsibao.panda.platform.user.workspace.user.OrganizationWorkspaceTest;
import com.gongsibao.panda.platform.user.workspace.user.RoleWorkspaceTest;
import com.gongsibao.panda.platform.user.workspace.user.UserWorkspaceTest;



@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	RoleReferenceTest.class,
	OrganizationReferenceTest.class,
	UserReferenceTest.class,
	
	OrganizationWorkspaceTest.class,
	UserWorkspaceTest.class,
	RoleWorkspaceTest.class,
	AuthWorkspaceTest.class,
	LoginLogWorkspaceTest.class,
	
	NavigationTest.class
	
})

public class AllTest {

}

