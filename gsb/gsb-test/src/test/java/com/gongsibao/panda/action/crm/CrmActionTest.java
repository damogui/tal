package com.gongsibao.panda.action.crm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.action.crm.customer.OpenMemberCustomerActionTest;
import com.gongsibao.panda.action.crm.customer.SaveCustomerActionTest;
import com.gongsibao.panda.action.crm.task.AbnormalActionTest;
import com.gongsibao.panda.action.crm.task.AllocationAutoActionTest;
import com.gongsibao.panda.action.crm.task.AllocationManualActionTest;
import com.gongsibao.panda.action.crm.task.FollowActionTest;
import com.gongsibao.panda.action.crm.task.RegainActionTest;
import com.gongsibao.panda.action.crm.task.ReleaseActionTest;
import com.gongsibao.panda.action.crm.task.RollbackActionTest;
import com.gongsibao.panda.action.crm.task.SaveActionTest;
import com.gongsibao.panda.action.crm.task.TransferActionTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	AllocationManualActionTest.class, 
	AllocationAutoActionTest.class,
	AbnormalActionTest.class, 
	FollowActionTest.class, 
	RegainActionTest.class, 
	ReleaseActionTest.class, 
	RollbackActionTest.class, 
	TransferActionTest.class,
	
	SaveActionTest.class,
	SaveCustomerActionTest.class,
	OpenMemberCustomerActionTest.class
})
		
public class CrmActionTest {

}
