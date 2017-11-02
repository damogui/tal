package com.gongsibao.panda;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	org.netsharp.meta.framework.AllTests.class,
	org.netsharp.meta.platform.AllTests.class,
	org.netsharp.meta.basebiz.AllTests.class,
	org.netsharp.wx.meta.AllTests.class,
    org.netsharp.cache.plugin.AllTests.class,
    org.netsharp.scrum.meta.AllTests.class,
    com.gongsibao.ma.AllTest.class,
    com.gongsibao.er.AllTest.class,
    com.gongsibao.crm.AllTest.class,
    com.gongsibao.taurus.AllTest.class,
	org.netsharp.meta.end.AllTests.class,
	
	})
public class AllTests {

}
