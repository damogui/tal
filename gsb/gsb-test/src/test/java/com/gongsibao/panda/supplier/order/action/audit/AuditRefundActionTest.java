package com.gongsibao.panda.supplier.order.action.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.refund.ActionAuditRefundSendMessage;
import com.gongsibao.trade.service.action.audit.refund.ActionAuditRefundVerify;
import com.gongsibao.trade.service.action.audit.refund.ActionAuditRefundWriteBack;

public class AuditRefundActionTest  extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/refund";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("退款审核");
		}

		createBean(beanPath, "1.验证", ActionAuditRefundVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditRefundWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditRefundSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
