package com.gongsibao.ma.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.dic.OrderAuditState;

public class MaOrderVPUnauditWorkspaceTest extends MaOrderWorkspaceTest{
	
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/ma/order/vpunaudit/list";
		urlForm = "/ma/order/vpunaudit/form";
		entity = MaOrder.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "VP待审核-订单";
		resourceNodeCode = "VP_UNAUDIT_"+MaOrder.class.getSimpleName();
		
		formToolbarPath = "ma/order/vp/audit";
		listFilter = "vpAuditState=" + OrderAuditState.UNAUDIT.getValue();
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = super.createForm(node);
		form.setReadOnly(true);
		return form;
	}
	
	@Test
	public void createFormToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(formToolbarPath);
			toolbar.setName("VP审核表单工具栏");
			toolbar.setResourceNode(node);
		}
		OperationType otAudit = operationTypeService.byCode(OperationTypes.audit);
		addToolbarItem(toolbar, "vpPass", "审核通过", "fa-check", "vpPass()", otAudit, 1);
		addToolbarItem(toolbar, "vpNoPass", "审核不通过", "fa-close", "vpNoPass()", otAudit, 2);
		toolbarService.save(toolbar);
	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = new PQueryProject();
		queryProject.toNew();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		
		addQueryItem(queryProject, "companyName", "企业名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sellingDemand.name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sellingDemand.mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "acquisitionDemand.name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "acquisitionDemand.mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "buyer.name", "业务人员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "state", "订单状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.audit);
		operationService.addOperation(node, OperationTypes.unaudit);
	}
}
