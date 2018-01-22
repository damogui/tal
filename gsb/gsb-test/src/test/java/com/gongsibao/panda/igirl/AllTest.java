package com.gongsibao.panda.igirl;
import com.gongsibao.panda.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.igirl.reference.NCLTwoReferenceTest;
import com.gongsibao.panda.igirl.workspace.MyAllTradeMarkCaseWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.NclOneWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.NclTwoWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.TradeMarkCaseAllWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.TradeMarkFollowWorkspaceTest;

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
		TradeMarkCaseAllWorkspaceTest.class,
		MyAllTradeMarkCaseWorkspaceTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}