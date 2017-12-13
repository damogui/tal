package com.gongsibao.panda.ma;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.ma.workspace.acquisition.AcquisitionDemandMyWorkspaceTest;
import com.gongsibao.panda.ma.workspace.acquisition.AcquisitionDemandWorkspaceTest;
import com.gongsibao.panda.ma.workspace.channel.AcquisitionDemandChannelWorkspaceTest;
import com.gongsibao.panda.ma.workspace.channel.SellingDemandChannelWorkspaceTest;
import com.gongsibao.panda.ma.workspace.order.MaOrderLeaderUnauditWorkspaceTest;
import com.gongsibao.panda.ma.workspace.order.MaOrderMyWorkspaceTest;
import com.gongsibao.panda.ma.workspace.order.MaOrderVPUnauditWorkspaceTest;
import com.gongsibao.panda.ma.workspace.order.MaOrderVPVerifiedWorkspaceTest;
import com.gongsibao.panda.ma.workspace.order.MaOrderWorkspaceTest;
import com.gongsibao.panda.ma.workspace.selling.SellingDemandMyWorkspaceTest;
import com.gongsibao.panda.ma.workspace.selling.SellingDemandUnauditWorkspaceTest;
import com.gongsibao.panda.ma.workspace.selling.SellingDemandWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		
		//出售
		SellingDemandWorkspaceTest.class,
		SellingDemandUnauditWorkspaceTest.class,
		SellingDemandMyWorkspaceTest.class,
		
		//收购
		AcquisitionDemandWorkspaceTest.class,
		AcquisitionDemandMyWorkspaceTest.class,
		
		//订单
		MaOrderWorkspaceTest.class,
		MaOrderMyWorkspaceTest.class,
		MaOrderVPUnauditWorkspaceTest.class,
		MaOrderLeaderUnauditWorkspaceTest.class,
		MaOrderVPVerifiedWorkspaceTest.class,
		
		//渠道
		AcquisitionDemandChannelWorkspaceTest.class,
		SellingDemandChannelWorkspaceTest.class,

		NavigationTest.class
		})
		
public class AllTest {

}
