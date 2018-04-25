package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.orderNewSave.ActionAuditOrderNewSaveSendMessage;
import com.gongsibao.trade.service.action.audit.orderNewSave.ActionAuditOrderNewSaveVerify;
import com.gongsibao.trade.service.action.audit.orderNewSave.ActionAuditOrderNewSaveWriteBack;

/**
 * 订单审核（改价审核）
 * @author Administrator
 *
 */
public class AuditOrderNewSaveActionTest extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/changeOrderPrice";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("订单审核");
		}

		createBean(beanPath, "1.验证", ActionAuditOrderNewSaveVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditOrderNewSaveWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditOrderNewSaveSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
