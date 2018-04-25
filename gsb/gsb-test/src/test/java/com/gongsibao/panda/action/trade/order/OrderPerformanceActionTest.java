package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.performance.ActionApplyOrderPerformanceAudit;
import com.gongsibao.trade.service.action.order.performance.ActionApplyOrderPerformanceLog;
import com.gongsibao.trade.service.action.order.performance.ActionApplyOrderPerformancePersist;
import com.gongsibao.trade.service.action.order.performance.ActionApplyOrderPerformanceSendMessage;
import com.gongsibao.trade.service.action.order.performance.ActionApplyOrderPerformanceVerify;

/*创建订单业绩action*/
public class OrderPerformanceActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/order/performance";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("创建订单业绩");
		}

		createBean(beanPath, "1.验证", ActionApplyOrderPerformanceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyOrderPerformancePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyOrderPerformanceAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionApplyOrderPerformanceSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionApplyOrderPerformanceLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
