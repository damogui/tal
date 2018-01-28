package com.gongsibao.panda.crm.action;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.plugin.bean.Bean;
import org.netsharp.plugin.bean.BeanPath;
import org.netsharp.plugin.bean.IBeanPathService;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

public class BaseActionTest {

	protected String resourceNodeCode = null;
	protected ResourceNode resourceNode = null;
	protected IBeanPathService beanPathService = ServiceFactory.create(IBeanPathService.class);
	protected IResourceNodeService resourceNodeService = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		this.resourceNode = resourceNodeService.byCode(resourceNodeCode);
	}

	protected Bean createBean(BeanPath beanPath,String name, String type, ResourceNode resourceNode, Integer seq) {

		Bean bean = new Bean();
		bean.toNew();
		bean.setName(name);
		bean.setType(type);
		bean.setResourceNode(resourceNode);
		bean.setSeq(seq);
		beanPath.getCodons().add(bean);
		return bean;
	}
}
