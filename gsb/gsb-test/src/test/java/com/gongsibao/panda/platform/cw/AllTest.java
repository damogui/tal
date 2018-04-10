package com.gongsibao.panda.platform.cw;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.cw.workspace.expense.ExpenseBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.loans.LoansBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.AllBillsWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.AllocationBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.CCBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.DoneBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.FinanceBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.other.TodoBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.workspace.payment.PaymentBillWorkspaceTest;
import com.gongsibao.panda.platform.cw.action.ActionAllTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 
	//财务报销工作区
	TodoBillWorkspaceTest.class,
	DoneBillWorkspaceTest.class,
	CCBillWorkspaceTest.class,
	FinanceBillWorkspaceTest.class,
	
	LoansBillWorkspaceTest.class,
	ExpenseBillWorkspaceTest.class,
	PaymentBillWorkspaceTest.class,
	AllocationBillWorkspaceTest.class,
	AllBillsWorkspaceTest.class,
	
	ActionAllTest.class,
	NavigationTest.class
})

public class AllTest {

}
