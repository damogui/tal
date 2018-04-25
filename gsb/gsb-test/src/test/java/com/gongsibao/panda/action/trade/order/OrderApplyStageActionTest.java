package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStageAudit;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStageLog;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStagePersist;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStageSendMessage;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStageVerify;
import com.gongsibao.trade.service.action.order.stage.ActionApplyStageWriteBack;

/**   
 * @ClassName:  OrderApplyStageActionTest   
 * @Description:TODO 申请分期
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:57:06   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderApplyStageActionTest extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/order/stage";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("申请分期");
		}

		createBean(beanPath, "1.验证", ActionApplyStageVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyStagePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "3.审核", ActionApplyStageAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "4.通知", ActionApplyStageSendMessage.class.getName(), resourceNode, 400);
		createBean(beanPath, "5.回写", ActionApplyStageWriteBack.class.getName(), resourceNode, 500);
		createBean(beanPath, "6.日志", ActionApplyStageLog.class.getName(), resourceNode, 600);
		beanPathService.save(beanPath);
	}
}
