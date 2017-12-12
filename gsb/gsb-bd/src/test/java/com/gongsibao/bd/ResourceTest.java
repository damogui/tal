package com.gongsibao.bd;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	//初始化资源
	@Before
	public void setup() {
		parentNodeName = "配置管理";
		parentNodeCode = "GSB_Config";
		pluginName = "配置管理";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	//创建资源节点
	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("配置管理", "GSB_Config_Manage", node.getId());
		{
			this.createResourceNodeVoucher(Dict.class.getName(), "字典列表", "Config_" + Dict.class.getSimpleName(),IDictService.class.getName(), node1.getId());
		}
	}
}