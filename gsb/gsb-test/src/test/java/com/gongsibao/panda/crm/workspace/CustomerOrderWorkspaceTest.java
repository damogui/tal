package com.gongsibao.panda.crm.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.CustomerOrder;

public class CustomerOrderWorkspaceTest extends WorkspaceCreationBase{

	
	@Before
	public void setup() {

		entity = CustomerOrder.class;
		urlList = "/crm/customer/order/list";
		urlForm = "/crm/customer/order/form";
		listPartName = formPartName = "客户订单";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_"+CustomerOrder.class.getSimpleName();
		
		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("客服配置列表");
		}
		addColumn(datagrid, "code", "订单号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "status", "订单状态", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "amount", "金额", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "customer.realName", "客户", ControlTypes.TEXT_BOX, 130);
//		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "code", "订单号", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "status", "订单状态", null, ControlTypes.ENUM_BOX, true);
		addFormField(form, "amount", "金额", null, ControlTypes.DECIMAL_FEN_BOX, true);
		addFormFieldRefrence(form, "customer.realName", "客户", null,  "CRM_Customer", true,false);
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
