package com.gongsibao.panda.platform.cw.action.expense;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.cw.service.action.expense.ActionExpenseApplyAudit;
import com.gongsibao.cw.service.action.expense.ActionExpenseApplyLog;
import com.gongsibao.cw.service.action.expense.ActionExpenseApplyPersist;
import com.gongsibao.cw.service.action.expense.ActionExpenseApplySendMessage;
import com.gongsibao.cw.service.action.expense.ActionExpenseApplyVerify;
import com.gongsibao.panda.supplier.crm.action.BaseActionTest;

public class ExpenseBillActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CW_Manage_Expense_Bill";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/cw/expense/form";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("报销申请");
		}
		createBean(beanPath, "1.验证", ActionExpenseApplyVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionExpenseApplyPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionExpenseApplyAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionExpenseApplySendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionExpenseApplyLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
	
}
