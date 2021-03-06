package com.gongsibao.panda.auth.authorization;

import com.gongsibao.panda.auth.authorization.platform.PlatformAuthorizationAllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.authorization.supplier.SupplierAuthorizationAllTest;

/**
 * @author hw
 *         处理授权
 */
@RunWith(Suite.class)
@SuiteClasses({


        PlatformAuthorizationAllTest.class,//职能、自营、非自营
        SupplierAuthorizationAllTest.class//服务商
})
public class AuthorizationAllTest {

}
