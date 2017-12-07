package com.gongsibao.u8;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.u8.reference.U8BankReferenceTest;
import com.gongsibao.u8.workspace.SetOfBooksWorkspace;
import com.gongsibao.u8.workspace.U8BankWorkspace;
import com.gongsibao.u8.workspace.VoucherLogWorkspace;


@RunWith(Suite.class)
@SuiteClasses({ 
		ResourceTest.class, 
		
		U8BankReferenceTest.class,
		
		//账套
		SetOfBooksWorkspace.class,
		
		//科目银行
		U8BankWorkspace.class,
		
		
		//凭证日志
		VoucherLogWorkspace.class,
		
		NavigationTest.class
		})
		
public class AllTest {

}
