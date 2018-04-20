package com.gongsibao.panda;

import com.gongsibao.panda.rest.RestAllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.AuthAllTest;
import com.gongsibao.panda.platform.PlatformAllTest;
import com.gongsibao.panda.supplier.SupplierAllTest;

@RunWith(Suite.class)
@SuiteClasses({
	
	//平台相关（不可删除）
	org.netsharp.meta.framework.AllTests.class,
	org.netsharp.meta.platform.AllTests.class,
	org.netsharp.meta.basebiz.AllTests.class,
	
	org.netsharp.wx.meta.AllTests.class,
    org.netsharp.cache.plugin.AllTests.class,
    org.netsharp.scrum.meta.AllTests.class,
    
	//1.业务平台管理
    PlatformAllTest.class,

    //2.服务商系统
	SupplierAllTest.class,

	// 13Rest接口
	RestAllTest.class,

	//处理资源Id
	org.netsharp.meta.end.AllTests.class,
	
	//放在最后处理
	//AuthAllTest.class
	})
public class ProdAllTests {

}
