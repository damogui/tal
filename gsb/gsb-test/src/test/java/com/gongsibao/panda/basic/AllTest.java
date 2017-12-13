package com.gongsibao.panda.basic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.basic.reference.SetOfBooksReferenceTest;
import com.gongsibao.panda.basic.reference.U8BankReferenceTest;
import com.gongsibao.panda.basic.workspace.config.DictWorkspaceTest;
import com.gongsibao.panda.basic.workspace.u8.SetOfBooksWorkspace;
import com.gongsibao.panda.basic.workspace.u8.U8BankWorkspaceTest;
import com.gongsibao.panda.basic.workspace.u8.VoucherLogWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 
	
	//参照
	SetOfBooksReferenceTest.class,
	U8BankReferenceTest.class,
	
	//基础配置
	DictWorkspaceTest.class,
	
	//U8配置
	SetOfBooksWorkspace.class,
	U8BankWorkspaceTest.class,
	VoucherLogWorkspaceTest.class,
	
	
	
	
	NavigationTest.class
	
})

public class AllTest {

}
