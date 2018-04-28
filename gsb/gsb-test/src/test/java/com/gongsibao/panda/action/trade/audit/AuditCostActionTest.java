package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.cost.ActionAuditCostSendMessage;
import com.gongsibao.trade.service.action.audit.cost.ActionAuditCostVerify;
import com.gongsibao.trade.service.action.audit.cost.ActionAuditCostWriteBack;

public class AuditCostActionTest  extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/cost";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("成本审核");
		}

		createBean(beanPath, "1.验证", ActionAuditCostVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditCostWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditCostSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
