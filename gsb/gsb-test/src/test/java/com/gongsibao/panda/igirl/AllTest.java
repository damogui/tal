package com.gongsibao.panda.igirl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.igirl.reference.NCLOneReferenceTest;
import com.gongsibao.panda.igirl.workspace.NclOneWorkspaceTest;
import com.gongsibao.panda.igirl.workspace.NclTwoWorkspaceTest;
@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class,
		NCLOneReferenceTest.class,
		NclOneWorkspaceTest.class,
		NclTwoWorkspaceTest.class,
		NavigationTest.class
		})
		
public class AllTest {

}