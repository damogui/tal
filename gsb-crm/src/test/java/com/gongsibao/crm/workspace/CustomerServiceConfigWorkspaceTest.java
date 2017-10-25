package com.gongsibao.crm.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.CustomerServiceConfig;

public class CustomerServiceConfigWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		entity = CustomerServiceConfig.class;
		urlList = "/crm/customer/service/config/list";
		urlForm = "/crm/customer/service/config/form";
		listPartName = formPartName = "客服配置";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_"+CustomerServiceConfig.class.getSimpleName();
		
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("客服配置列表");
		}
		addColumn(datagrid, "employee.name", "人员", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "swtServiceId", "商务通Id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormFieldRefrence(form, "employee.name", "人员", null, "Employee", true, false);
		addFormField(form, "swtServiceId", "商务通Id", null, ControlTypes.TEXT_BOX, true);
		return form;
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}
}
