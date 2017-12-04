package com.gongsibao.trade;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory
			.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "订单查询";
		parentNodeCode = "GSB_TRADE";
		pluginName = "订单查询";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("订单查询", "GSB_TRADE_Enquiry",
				node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单查询列表",
					"TRADE_" + SoOrder.class.getSimpleName(),
					IOrderService.class.getName(), node1.getId());
		}
	}
}
