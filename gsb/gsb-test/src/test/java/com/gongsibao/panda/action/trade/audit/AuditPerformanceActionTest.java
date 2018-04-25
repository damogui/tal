package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.performance.ActionAuditPerformanceSendMessage;
import com.gongsibao.trade.service.action.audit.performance.ActionAuditPerformanceVerify;
import com.gongsibao.trade.service.action.audit.performance.ActionAuditPerformanceWriteBack;

/*订单业绩审核*/
public class AuditPerformanceActionTest  extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/performance";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("订单业绩审核");
		}

		createBean(beanPath, "1.验证", ActionAuditPerformanceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditPerformanceWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditPerformanceSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}
