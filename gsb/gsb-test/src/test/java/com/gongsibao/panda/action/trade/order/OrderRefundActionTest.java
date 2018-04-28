package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundAudit;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundLog;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundPersist;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundSendMessage;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundVerify;
import com.gongsibao.trade.service.action.order.refund.ActionApplyRefundWriteBack;

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

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
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
		//createBean(beanPath, "4.通知", ActionApplyRefundSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.回写", ActionApplyRefundWriteBack.class.getName(), resourceNode, 500);
		createBean(beanPath, "6.日志", ActionApplyRefundLog.class.getName(), resourceNode, 600);
		beanPathService.save(beanPath);
	}
}
