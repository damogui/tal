package com.gongsibao.panda.operation.workspace.wanda;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.cms.ProductView;

public class WanDaProductWorkspaceTest extends WorkspaceCreationBase{

	
	@Before
	public void setup() {

		entity = ProductView.class;
		urlList = "/wanda/product/list";
		listPartName = formPartName = "服务列表";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_WANDA_"+ProductView.class.getSimpleName();
		
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("服务列表");
			datagrid.setShowCheckbox(false);
		}
		addColumn(datagrid, "serviceClass", "服务分类", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "showprice", "价格", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "total", "累计销量", ControlTypes.NUMBER_BOX, 130);
		//addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}
	
	/*protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "serviceClass", "服务分类", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "prodName", "服务名称", null, ControlTypes.ENUM_BOX, true);
		addFormField(form, "showprice", "价格", null, ControlTypes.TEXT_BOX, true);
		addFormField(form, "total", "累计销量", null,  ControlTypes.NUMBER_BOX, true);
		return form;
	}*/
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "prodName", "服务名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		
	}
}
