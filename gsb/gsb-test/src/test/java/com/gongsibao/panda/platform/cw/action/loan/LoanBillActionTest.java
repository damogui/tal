package com.gongsibao.panda.platform.cw.action.loan;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.cw.service.action.loan.ActionLoanApplyAudit;
import com.gongsibao.cw.service.action.loan.ActionLoanApplyLog;
import com.gongsibao.cw.service.action.loan.ActionLoanApplyPersist;
import com.gongsibao.cw.service.action.loan.ActionLoanApplySendMessage;
import com.gongsibao.cw.service.action.loan.ActionLoanApplyVerify;
import com.gongsibao.panda.action.BaseActionTest;


public class LoanBillActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "GSB_CW_Manage_Loan_Bill";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/cw/loan/form";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("借款申请");
		}
		createBean(beanPath, "1.验证", ActionLoanApplyVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionLoanApplyPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionLoanApplyAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionLoanApplySendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionLoanApplyLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
