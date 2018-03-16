package com.gongsibao.panda.auth.authorization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.authorization.supplier.SupplierAuthorizationAllTest;

/**
 * @author hw
 * 处理授权
 */
@RunWith(Suite.class)
@SuiteClasses({
	
//	PlatformAuthorizationAllTest.class,
	SupplierAuthorizationAllTest.class
})
public class AuthorizationAllTest {

}
