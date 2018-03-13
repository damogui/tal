package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundAudit;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundLog;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundPersist;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundSendMessage;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundVerify;

/**   
 * @ClassName:  OrderRefundActionTest   
 * @Description:TODO 申请退款
 * @author: 韩伟
 * @date:   2018年3月12日 下午5:06:59   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderRefundActionTest  extends BaseActionTest{


	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}


	@Test
	public void save() {
		
		String pathName = "gsb/crm/order/refund";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("申请退款");
		}

		createBean(beanPath, "1.验证", ActionApplyRefundVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyRefundPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyRefundAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionApplyRefundSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.日志", ActionApplyRefundLog.class.getName(), resourceNode, 500);
		beanPathService.save(beanPath);
	}
}
