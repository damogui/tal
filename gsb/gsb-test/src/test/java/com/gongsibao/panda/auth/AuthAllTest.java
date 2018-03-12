package com.gongsibao.panda.auth;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.authorization.AuthorizationAllTest;
import com.gongsibao.panda.auth.role.RoleGroupTest;
import com.gongsibao.panda.auth.role.RoleTest;

/**
 * @author hw
 * 权限处理，执行顺序不可变
 */
@RunWith(Suite.class)
@SuiteClasses({
	
	RoleGroupTest.class,
	RoleTest.class,
	AuthorizationAllTest.class
})
public class AuthAllTest {

}
