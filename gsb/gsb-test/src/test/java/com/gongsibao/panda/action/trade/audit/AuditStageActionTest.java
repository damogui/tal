package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.stage.ActionAuditStageSendMessage;
import com.gongsibao.trade.service.action.audit.stage.ActionAuditStageVerify;
import com.gongsibao.trade.service.action.audit.stage.ActionAuditStageWriteBack;

public class AuditStageActionTest extends BaseActionTest {
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/stage";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("分期审核");
		}

		createBean(beanPath, "1.验证", ActionAuditStageVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditStageWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditStageSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
