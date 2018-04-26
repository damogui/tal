package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverAudit;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverLog;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverPersist;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverSendMessage;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverVerify;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverWriteBack;

/**   
 * @ClassName:  CarryoverActionTest   
 * @Description:TODO 申请结转
 * @author: 韩伟
 * @date:   2018年3月12日 下午5:06:49   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderCarryoverActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/order/carryover";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("申请结转");
		}

		createBean(beanPath, "1.验证", ActionApplyCarryoverVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyCarryoverPersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyCarryoverAudit.class.getName(), resourceNode, 300);
		//createBean(beanPath, "4.通知", ActionApplyCarryoverSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.回写", ActionApplyCarryoverWriteBack.class.getName(), resourceNode, 500);
		createBean(beanPath, "6.日志", ActionApplyCarryoverLog.class.getName(), resourceNode, 600);
		beanPathService.save(beanPath);
	}
}
