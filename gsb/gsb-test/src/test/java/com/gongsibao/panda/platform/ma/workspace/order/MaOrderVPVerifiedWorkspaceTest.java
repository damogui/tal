package com.gongsibao.panda.platform.ma.workspace.order;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.dic.OrderAuditState;

public class MaOrderVPVerifiedWorkspaceTest extends MaOrderWorkspaceTest{
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/ma/order/vpverified/list";
		urlForm = "/ma/order/vpverified/form";
		entity = MaOrder.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "审核通过-订单";
		resourceNodeCode = "VP_VERIFIED_"+MaOrder.class.getSimpleName();
		
		listFilter = "vpAuditState=" + OrderAuditState.VERIFIED.getValue();
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {
		
		PForm form = super.createForm(node);
		form.setReadOnly(true);
		return form;
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
	}
}
