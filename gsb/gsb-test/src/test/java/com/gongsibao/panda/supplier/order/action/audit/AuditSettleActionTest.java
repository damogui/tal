package com.gongsibao.panda.supplier.order.action.audit;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPaySendMessage;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPayVerify;
import com.gongsibao.trade.service.action.audit.pay.ActionAuditPayWriteBack;
import com.gongsibao.trade.service.action.audit.settle.ActionAuditSettleSendMessage;
import com.gongsibao.trade.service.action.audit.settle.ActionAuditSettleVerify;
import com.gongsibao.trade.service.action.audit.settle.ActionAuditSettleWriteBack;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

/*回款审核*/
public class AuditSettleActionTest extends BaseActionTest{
	@Before
	public void setup() {
		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/platform/audit/settle";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("结算审核");
		}

		createBean(beanPath, "1.验证", ActionAuditSettleVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditSettleWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.通知", ActionAuditSettleSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
