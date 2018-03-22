package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayAudit;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayLog;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayPersist;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPaySendMessage;
import com.gongsibao.trade.service.action.order.pay.ActionApplyPayVerify;

/**   
 * @ClassName:  PayPerformanceActionTest   
 * @Description:TODO 回款业绩
 * @author: 郭佳
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
		
		String pathName = "gsb/crm/order/pay";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("创建回款");
		}

		createBean(beanPath, "1.验证", ActionApplyPayVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyPayPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyPayAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionApplyPaySendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionApplyPayLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
