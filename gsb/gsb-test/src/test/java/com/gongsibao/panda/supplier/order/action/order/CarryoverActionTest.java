package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverAudit;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverPersist;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverSendMessage;
import com.gongsibao.trade.service.action.order.carryover.ActionApplyCarryoverVerify;

/**   
 * @ClassName:  CarryoverActionTest   
 * @Description:TODO 申请结转
 * @author: 韩伟
 * @date:   2018年3月12日 下午5:06:49   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class CarryoverActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
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
		createBean(beanPath, "4.审核", ActionApplyCarryoverAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "5.通知", ActionApplyCarryoverSendMessage.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}
