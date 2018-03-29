package com.gongsibao.panda.platform.cw.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.cw.action.audit.AuditRecordActionTest;
import com.gongsibao.panda.platform.cw.action.loan.LoanBillActionTest;

@RunWith(Suite.class)
@SuiteClasses({
       
	LoanBillActionTest.class,
	AuditRecordActionTest.class,
   
})

public class ActionAllTest {

}
