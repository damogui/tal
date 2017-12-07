package com.gongsibao.trade.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.SoOrderListPart;



public class SoOrderWorkspaceTest extends WorkspaceCreationBase{

	
	@Before
	public void setup() {

		entity = SoOrder.class;
		urlList = "/trade/order/list";
		listPartName = formPartName = "订单查询列表";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "TRADE_"+SoOrder.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
		//扩展
		listPartServiceController = SoOrderListPart.class.getName();
		listPartJsController = SoOrderListPart.class.getName();
		listPartImportJs = "/gsb/trade/js/soOrder.list.part.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		/*{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("订单查询列表");
			datagrid.setShowCheckbox(false);
			
		}*/
		datagrid.setShowCheckbox(false);
		datagrid.setToolbar("panda/datagrid/row/edit");
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		PDatagridColumn column= null;
		addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "accountName", "客户名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 100);{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		addColumn(datagrid, "payTime", "订单时间", ControlTypes.DATE_BOX, 130);
		column = addColumn(datagrid, "processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);{
			column.setAlign(DatagridAlign.CENTER);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "accountName", "客户名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "prodName", "服务名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "payTime", "订单时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
	}
}
