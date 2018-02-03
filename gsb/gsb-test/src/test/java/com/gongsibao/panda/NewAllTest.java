package com.gongsibao.panda;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	
	//平台相关（不可删除）
	org.netsharp.meta.framework.AllTests.class,
	org.netsharp.meta.platform.AllTests.class,
	org.netsharp.meta.basebiz.AllTests.class,
	
//	org.netsharp.wx.meta.AllTests.class,
//  org.netsharp.cache.plugin.AllTests.class,
//    org.netsharp.scrum.meta.AllTests.class,
    
//	//1.基础信息
	com.gongsibao.panda.basic.AllTest.class,

    //2.用户中心
//	com.gongsibao.panda.user.AllTest.class,
	
    //2.客户管理
	  com.gongsibao.panda.crm.AllTest.class,
	
    //3.招商CRM
	//com.gongsibao.panda.franchisee.AllTest.class,
	
    //4.股转中心
	//com.gongsibao.panda.ma.AllTest.class,
	
    //5.运营管理
	com.gongsibao.panda.operation.AllTest.class,
	
    //6.商品管理
//	com.gongsibao.panda.product.AllTest.class,

	
    //8.交易中心
	//com.gongsibao.panda.trade.AllTest.class,
	
    //9.报表中心
	 //com.gongsibao.panda.report.AllTest.class,

    //10.igri
//	com.gongsibao.panda.igirl.AllTest.class,


    //11.gardian
// 	com.gongsibao.panda.gardian.AllTest.class,
	//处理资源Id
	org.netsharp.meta.end.AllTests.class,
	})
public class NewAllTest {

}
