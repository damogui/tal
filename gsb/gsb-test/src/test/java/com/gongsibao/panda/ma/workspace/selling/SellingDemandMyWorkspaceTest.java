package com.gongsibao.panda.ma.workspace.selling;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.SellingDemand;

public class SellingDemandMyWorkspaceTest extends SellingDemandWorkspaceTest{

	@Override
	@Before
	public void setup() {
		super.setup();
		urlList = "/ma/selling/my/list";
		urlForm = "/ma/selling/my/form";
		entity = SellingDemand.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "我的-出售需求";
		listFilter = "creatorId='{userId}'";
		resourceNodeCode = "My"+SellingDemand.class.getSimpleName();
	}
	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}
