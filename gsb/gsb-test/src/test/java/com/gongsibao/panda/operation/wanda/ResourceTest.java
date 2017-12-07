package com.gongsibao.panda.operation.wanda;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cms.base.IProductViewService;
import com.gongsibao.entity.cms.ProductView;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "服务管理";
		parentNodeCode = "GSB_CMS";
		pluginName = "服务管理";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("服务管理", "GSB_CMS_Manager", node.getId());
		{
			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "CMS_" + ProductView.class.getSimpleName(),
					IProductViewService.class.getName(), node1.getId());
		}
	}
}
