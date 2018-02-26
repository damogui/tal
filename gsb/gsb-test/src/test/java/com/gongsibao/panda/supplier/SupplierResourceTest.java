package com.gongsibao.panda.supplier;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SupplierResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "服务商系统";
		parentNodeCode = "Gsb_Supplier_System";
		pluginName = "服务商系统";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
	}
}
