package com.gongsibao.er;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.er.Order;
import com.gongsibao.er.base.IOrderService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "任务管理";
		parentNodeCode = "GSB_ER";
		pluginName = "任务管理";
		seq = 2;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("任务管理", "ER_Task", node.getId());
		{
			this.createResourceNodeVoucher(Order.class.getName(), "指挥中心", "ER_Task_1_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "全部订单", "ER_Task_2_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "我的任务", "ER_Task_3_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "待审核", "ER_Task_4_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("配置管理", "ER_Config", node.getId());
		{
			this.createResourceNodeVoucher(Order.class.getName(), "流程管理", "ER_Config_1_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "模版管理", "ER_Config_2_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "客户管理", "ER_Config_3_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "商品管理", "ER_Config_4_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Order.class.getName(), "任务设置", "ER_Config_5_" + Order.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
		}
	}
}
