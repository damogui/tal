package com.gongsibao.panda.platform.cw.action.payment;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.cw.service.action.payment.ActionPaymentApplyAudit;
import com.gongsibao.cw.service.action.payment.ActionPaymentApplyLog;
import com.gongsibao.cw.service.action.payment.ActionPaymentApplyPersist;
import com.gongsibao.cw.service.action.payment.ActionPaymentApplySendMessage;
import com.gongsibao.cw.service.action.payment.ActionPaymentApplyVerify;
import com.gongsibao.panda.action.BaseActionTest;

public class PaymentBillActionTest extends BaseActionTest{

	
	@Before
	public void setup() {

		resourceNodeCode = "GSB_CW_Manage_Payment_Bill";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/cw/payment/form";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("借款申请");
		}
		createBean(beanPath, "1.验证", ActionPaymentApplyVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionPaymentApplyPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionPaymentApplyAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionPaymentApplySendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionPaymentApplyLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
