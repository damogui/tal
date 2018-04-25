package com.gongsibao.panda.action.trade.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderAutoAllocation;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderChangePriceAudit;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderCompany;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderCoupon;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderPersist;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderPrice;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderSendMessage;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderSplit;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderTrademark;
import com.gongsibao.trade.service.action.order.save.ActionSaveOrderVerify;

public class OrderNewSaveActionTest extends BaseActionTest {
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {

		String pathName = "gsb/crm/order/save";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("创建订单");
		}

		createBean(beanPath, "1.验证", ActionSaveOrderVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.拆分", ActionSaveOrderSplit.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.商标", ActionSaveOrderTrademark.class.getName(), resourceNode, 300);
		createBean(beanPath, "5.关联公司", ActionSaveOrderCompany.class.getName(), resourceNode, 400);
		createBean(beanPath, "6.价格", ActionSaveOrderPrice.class.getName(), resourceNode, 500);
		createBean(beanPath, "7.持久化", ActionSaveOrderPersist.class.getName(), resourceNode, 600);
		createBean(beanPath, "3.优惠劵", ActionSaveOrderCoupon.class.getName(), resourceNode, 700);
		createBean(beanPath, "8.改价审核", ActionSaveOrderChangePriceAudit.class.getName(), resourceNode, 800);
		createBean(beanPath, "9.分配", ActionSaveOrderAutoAllocation.class.getName(), resourceNode, 900);
		createBean(beanPath, "10.消息", ActionSaveOrderSendMessage.class.getName(), resourceNode, 1000);
		beanPathService.save(beanPath);
	}
}
