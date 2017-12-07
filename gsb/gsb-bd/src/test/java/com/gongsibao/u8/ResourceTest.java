package com.gongsibao.u8;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.u8.base.ISetOfBooksService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	//初始化资源
	@Before
	public void setup() {
		parentNodeName = "U8管理";
		parentNodeCode = "GSB_U8";
		pluginName = "U8管理";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	//创建资源节点
	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("U8管理", "GSB_U8_Manage", node.getId());
		{
			this.createResourceNodeVoucher(SetOfBooks.class.getName(), "账套列表", "U8_" + SetOfBooks.class.getSimpleName(),ISetOfBooksService.class.getName(), node1.getId());		
			
		}
	}
}
