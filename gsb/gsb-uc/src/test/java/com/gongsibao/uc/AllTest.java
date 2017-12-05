package com.gongsibao.uc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.uc.reference.OrganizationReferenceTest;
import com.gongsibao.uc.reference.RoleReferenceTest;
import com.gongsibao.uc.reference.UserReferenceTest;
import com.gongsibao.uc.workspace.AuthWorkspaceTest;
import com.gongsibao.uc.workspace.LoginLogWorkspaceTest;
import com.gongsibao.uc.workspace.OrganizationWorkspaceTest;
import com.gongsibao.uc.workspace.RoleWorkspaceTest;
import com.gongsibao.uc.workspace.UserWorkspaceTest;

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
