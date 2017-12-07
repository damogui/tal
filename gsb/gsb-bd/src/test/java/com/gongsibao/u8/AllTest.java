package com.gongsibao.u8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.u8.workspace.SetOfBooksWorkspace;


@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		//账套
		SetOfBooksWorkspace.class,
		NavigationTest.class
		})
		
public class AllTest {

}
