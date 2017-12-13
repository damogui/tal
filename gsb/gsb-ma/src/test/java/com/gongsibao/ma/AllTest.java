package com.gongsibao.ma;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.ma.workspace.acquisition.AcquisitionDemandMyWorkspaceTest;
import com.gongsibao.ma.workspace.acquisition.AcquisitionDemandWorkspaceTest;
import com.gongsibao.ma.workspace.channel.AcquisitionDemandChannelWorkspaceTest;
import com.gongsibao.ma.workspace.channel.SellingDemandChannelWorkspaceTest;
import com.gongsibao.ma.workspace.order.MaOrderLeaderUnauditWorkspaceTest;
import com.gongsibao.ma.workspace.order.MaOrderMyWorkspaceTest;
import com.gongsibao.ma.workspace.order.MaOrderVPUnauditWorkspaceTest;
import com.gongsibao.ma.workspace.order.MaOrderVPVerifiedWorkspaceTest;
import com.gongsibao.ma.workspace.order.MaOrderWorkspaceTest;
import com.gongsibao.ma.workspace.selling.SellingDemandMyWorkspaceTest;
import com.gongsibao.ma.workspace.selling.SellingDemandUnauditWorkspaceTest;
import com.gongsibao.ma.workspace.selling.SellingDemandWorkspaceTest;


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
