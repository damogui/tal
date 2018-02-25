package com.gongsibao.panda.igirl;
import com.gongsibao.panda.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.igirl.reference.NCLTwoReferenceTest;
import com.gongsibao.panda.igirl.workspace.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		NCLOneReferenceTest.class,
		NCLTwoReferenceTest.class,
		NclOneWorkspaceTest.class,
		NclTwoWorkspaceTest.class,
		NclBatchWorkspaceTest.class,
		ConfigWorkspaceTest.class,
		TradeMarkCaseAllWorkspaceTest.class,
		MyAllTradeMarkCaseWorkspaceTest.class,
		TradeMarkFollowWorkspaceTest.class,
		MyTradeMarkFollowWorkspaceTest.class,
		DpAllTradeMarkCaseWorkspaceTest.class,
		DpTradeMarkFollowWorkspaceTest.class,
		SupplierNewInfoWorkspaceTest.class,
		SupplierSiteInfoWorkspaceTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}