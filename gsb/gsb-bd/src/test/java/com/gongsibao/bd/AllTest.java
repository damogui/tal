package com.gongsibao.bd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.bd.reference.DictReferenceTest;
import com.gongsibao.bd.workspace.DictWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		
//		DictReferenceTest.class,
		
		//字典
		DictWorkspaceTest.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}
