package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.carryover.ActionAuditCarryoverSendMessage;
import com.gongsibao.trade.service.action.audit.carryover.ActionAuditCarryoverVerify;
import com.gongsibao.trade.service.action.audit.carryover.ActionAuditCarryoverWriteBack;

public class AuditCarryoverActionTest  extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/carryover";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("结转审核");
		}

		createBean(beanPath, "1.验证", ActionAuditCarryoverVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditCarryoverWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditCarryoverSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
