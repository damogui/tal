package com.gongsibao.panda.ma.workspace.selling;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.DemandState;

public class SellingDemandUnauditWorkspaceTest extends SellingDemandWorkspaceTest{
	@Override
	@Before
	public void setup() {
		super.setup();
		listToolbarPath="panda/datagrid/voucher";
		urlList = "/ma/selling/unaudit/list";
		urlForm = "/ma/selling/unaudit/form";
		entity = SellingDemand.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "待审核-出售需求";
		listFilter = "state="+DemandState.UNAUDIT.getValue();
		resourceNodeCode = "UNAUDIT"+SellingDemand.class.getSimpleName();
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		datagrid.getColumns().clear();
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "province.name", "注册省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "注册城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "注册区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "出售人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "mobile", "出售电话", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "createTime", "登记时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "creator", "登记人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "selingStatus", "出售状态", ControlTypes.ENUM_BOX, 80);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		queryProject.toNew();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		addQueryItem(queryProject, "companyName", "企业名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "companyName", "注册地区", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "selingStatus", "出售状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.audit);
	}
}
