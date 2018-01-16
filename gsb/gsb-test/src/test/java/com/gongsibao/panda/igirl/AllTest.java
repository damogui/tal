package com.gongsibao.panda.igirl;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.panda.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.igirl.reference.NCLTwoReferenceTest;
import com.gongsibao.panda.igirl.workspace.NclOneWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.NclTwoWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.TradeMarkCaseAllWorkspaceTest;
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
		NavigationTest.class
		})
		
public class AllTest {

}