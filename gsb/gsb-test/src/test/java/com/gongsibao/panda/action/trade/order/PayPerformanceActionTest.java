package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.performance.pay.ActionApplyPayPerformanceAudit;
import com.gongsibao.trade.service.action.order.performance.pay.ActionApplyPayPerformanceLog;
import com.gongsibao.trade.service.action.order.performance.pay.ActionApplyPayPerformancePersist;
import com.gongsibao.trade.service.action.order.performance.pay.ActionApplyPayPerformanceSendMessage;
import com.gongsibao.trade.service.action.order.performance.pay.ActionApplyPayPerformanceVerify;

public class PayPerformanceActionTest extends BaseActionTest {

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}


	@Test
	public void save() {
		
		String pathName = "gsb/crm/order/pay/performance";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("创建回款业绩");
		}

		createBean(beanPath, "1.验证", ActionApplyPayPerformanceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyPayPerformancePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyPayPerformanceAudit.class.getName(), resourceNode, 300);
		//createBean(beanPath, "4.通知", ActionApplyPayPerformanceSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionApplyPayPerformanceLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
