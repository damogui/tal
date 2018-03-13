package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPerformanceAudit;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPerformanceLog;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPerformancePersist;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPerformanceSendMessage;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPerformanceVerify;

/**   
 * @ClassName:  PayPerformanceActionTest   
 * @Description:TODO 回款业绩
 * @author: 韩伟
 * @date:   2018年3月12日 下午5:02:48   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class PayPerformanceActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
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
			beanPath.setName("申请回款业绩");
		}

		createBean(beanPath, "1.验证", ActionApplyPayPerformanceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyPayPerformancePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyPayPerformanceAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionApplyPayPerformanceSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionApplyPayPerformanceLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
