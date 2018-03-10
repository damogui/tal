package com.gongsibao.panda.platform;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({

	//1.基础信息
	com.gongsibao.panda.platform.basic.AllTest.class,

    //2.用户中心
	com.gongsibao.panda.platform.user.AllTest.class,

    //3.招商CRM
	com.gongsibao.panda.platform.franchisee.FranchiseeAllTest.class,
	
    //4.股转中心
	com.gongsibao.panda.platform.ma.MaAllTest.class,
	
    //5.运营管理
	com.gongsibao.panda.platform.operation.AllTest.class,

    //8.交易中心
	com.gongsibao.panda.platform.trade.AllTest.class,
	
    //9.报表中心
	 com.gongsibao.panda.platform.report.AllTest.class,

    //11.gardian
 	com.gongsibao.panda.platform.gardian.AllTest.class,

	})
public class PlatformAllTest {

}
