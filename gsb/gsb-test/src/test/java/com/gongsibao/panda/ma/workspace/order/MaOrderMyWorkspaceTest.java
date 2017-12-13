package com.gongsibao.panda.ma.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.ma.web.MaOrderFormPart;

public class MaOrderMyWorkspaceTest extends MaOrderWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/ma/order/my/list";
		urlForm = "/ma/order/my/form";
		entity = MaOrder.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "我的订单";
		resourceNodeCode = "My"+MaOrder.class.getSimpleName();
		listFilter = "creatorId='{userId}'";
		formServiceController = MaOrderFormPart.class.getName();
		formJsController = MaOrderFormPart.class.getName();
		formJsImport = "/gsb/ma/js/order.form.part.js";
		
		formToolbarPath = "ma/order/my";
	}
	
	@Test
	public void createFormToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/form/edit");
			toolbar.setPath(formToolbarPath);
			toolbar.setName("我的订单表单工具栏");
			toolbar.setResourceNode(node);
		}
		OperationType otAudit = operationTypeService.byCode(OperationTypes.audit);
		addToolbarItem(toolbar, "submitAudit", "提交审核", "fa-upload", "submitAudit()", otAudit,800);
		toolbarService.save(toolbar);
	}
	
	@Override
	protected void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.audit);
		service.addOperation(node, OperationTypes.delete);
	}
}
