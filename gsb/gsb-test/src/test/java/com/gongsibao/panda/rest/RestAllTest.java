package com.gongsibao.panda.rest;

import com.gongsibao.panda.rest.order.OrderOnlinePayActionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        RestResourceTest.class,
        OrderOnlinePayActionTest.class,
        RestNavigationTest.class
})
public class RestAllTest {

}
