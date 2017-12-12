package com.gongsibao.panda.iduty;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.er.Order;
import com.gongsibao.er.base.IOrderService;

public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_IDuty";

	@Before
	public void setup() {

		parentNodeName = "IDuty";
		parentNodeCode = resourcePrefix;
		pluginName = "IDuty";
		seq = 2;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("任务管理", prefix + "_Task", node.getId());
		{
			this.createResourceNodeVoucher(Order.class.getName(), "指挥中心", node1.getCode() + "_Command_Center" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "全部订单", node1.getCode() + "_All_Order" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "我的任务", node1.getCode() + "_My_Task" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "待审核", node1.getCode() + "_Await_Order" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("配置管理", prefix + "_Config", node.getId());
		{
			this.createResourceNodeVoucher(Order.class.getName(), "流程管理", node1.getCode() + "_Flow" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "模版管理", node1.getCode() + "_Template" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "客户管理", node1.getCode() + "_Customer" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "商品管理", node1.getCode() + "_Product" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "任务设置", node1.getCode() + "_Task" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
		}
	}
}