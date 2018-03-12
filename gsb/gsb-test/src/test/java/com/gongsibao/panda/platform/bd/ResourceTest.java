package com.gongsibao.panda.platform.bd;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.bd.base.IBdUserBebaviorService;
import com.gongsibao.entity.bd.BdUserBehaviorLog;

public class ResourceTest extends ResourceCreationBase{


	public static String resourcePrefix = "gsb";

	@Before
	public void setup() {

		parentNodeName = "用户行为统计";
		parentNodeCode = ResourceTest.resourcePrefix;
		pluginName = "用户行为统计";
		seq = 9;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("用户行为统计", prefix + "_user", node.getId());
		{
			this.createResourceNodeVoucher(BdUserBehaviorLog.class.getName(), "用户行为统计", node1.getCode() + "_behavior", IBdUserBebaviorService.class.getName(), node1.getId());
		}

	}
	
}
