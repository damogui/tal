package com.gongsibao.panda.crm.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.crm.action.customer.SaveCustomerActionTest;
import com.gongsibao.panda.crm.action.task.AbnormalActionTest;
import com.gongsibao.panda.crm.action.task.AllocationManualActionTest;
import com.gongsibao.panda.crm.action.task.FollowActionTest;
import com.gongsibao.panda.crm.action.task.RegainActionTest;
import com.gongsibao.panda.crm.action.task.ReleaseActionTest;
import com.gongsibao.panda.crm.action.task.RollbackActionTest;
import com.gongsibao.panda.crm.action.task.SaveActionTest;
import com.gongsibao.panda.crm.action.task.TransferActionTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	AllocationManualActionTest.class, 
	AbnormalActionTest.class, 
	FollowActionTest.class, 
	RegainActionTest.class, 
	ReleaseActionTest.class, 
	RollbackActionTest.class, 
	TransferActionTest.class,
	
	SaveActionTest.class,
	SaveCustomerActionTest.class,
})
		
public class ActionAllTest {

}
