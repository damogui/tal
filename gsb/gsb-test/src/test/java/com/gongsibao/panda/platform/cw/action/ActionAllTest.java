package com.gongsibao.panda.platform.cw.action;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.cw.action.allocation.AllocationBillActionTest;
import com.gongsibao.panda.platform.cw.action.audit.AuditRecordActionTest;
import com.gongsibao.panda.platform.cw.action.expense.ExpenseBillActionTest;
import com.gongsibao.panda.platform.cw.action.loan.LoanBillActionTest;
import com.gongsibao.panda.platform.cw.action.payment.PaymentBillActionTest;

@RunWith(Suite.class)
@SuiteClasses({
       
	LoanBillActionTest.class,
	AuditRecordActionTest.class,
	ExpenseBillActionTest.class,
	PaymentBillActionTest.class,
	AllocationBillActionTest.class,
})

public class ActionAllTest {

}
