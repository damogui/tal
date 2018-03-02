package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.crm.action.BaseActionTest;
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

public class SaveOrderActionTest extends BaseActionTest {
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

		createBean(beanPath, "验证", ActionSaveOrderVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "拆分", ActionSaveOrderSplit.class.getName(), resourceNode, 200);
		createBean(beanPath, "优惠劵", ActionSaveOrderCoupon.class.getName(), resourceNode, 300);
		createBean(beanPath, "商标", ActionSaveOrderTrademark.class.getName(), resourceNode, 400);
		createBean(beanPath, "关联公司", ActionSaveOrderCompany.class.getName(), resourceNode, 500);
		createBean(beanPath, "价格", ActionSaveOrderPrice.class.getName(), resourceNode, 600);
		createBean(beanPath, "保存", ActionSaveOrderPersist.class.getName(), resourceNode, 700);
		createBean(beanPath, "改价审核", ActionSaveOrderChangePriceAudit.class.getName(), resourceNode, 800);
		createBean(beanPath, "分配", ActionSaveOrderAutoAllocation.class.getName(), resourceNode, 900);
		createBean(beanPath, "消息", ActionSaveOrderSendMessage.class.getName(), resourceNode, 1000);
		beanPathService.save(beanPath);
	}
}
