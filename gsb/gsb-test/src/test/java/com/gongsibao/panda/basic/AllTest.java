package com.gongsibao.panda.basic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.basic.workspace.u8.SetOfBooksWorkspace;
import com.gongsibao.panda.operation.NavigationTest;
import com.gongsibao.panda.operation.ResourceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	//U8配置
	SetOfBooksWorkspace.class,

	
	
	NavigationTest.class
	
})

public class AllTest {

}
