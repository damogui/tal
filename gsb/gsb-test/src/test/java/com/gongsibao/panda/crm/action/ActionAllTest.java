package com.gongsibao.panda.crm.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AllocationActionTest.class, 
	AbnormalActionTest.class, 
	FollowActionTest.class, 
	RegainActionTest.class, 
	ReleaseActionTest.class, 
	RollbackActionTest.class, 
	TransferActionTest.class,
	
	AddActionTest.class,
	UpdateActionTest.class
})
		
public class ActionAllTest {

}
