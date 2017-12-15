package com.gongsibao.panda.basic.workspace.u8;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.u8.web.ManualVoucherOrderDTOController;

public class ManualVoucherOrderDTOWorkspaceTest extends WorkspaceCreationBase  {

	@Before
	public void setup() {

		entity = ManualVoucherOrderDTO.class;//实体
		urlList = "/basic/u8/manualvoucherorder/list";//列表的url
		urlForm = "/basic/u8/manualvoucherorder/form";//弹出框的url
		listPartName = formPartName = "手动凭证订单列表";
		meta = MtableManager.getMtable(entity);//获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + ManualVoucherOrderDTO.class.getSimpleName();//菜单节点码（名称）
		
		formOpenMode = OpenMode.WINDOW;//编辑框打开的形式
		openWindowHeight=400;
		openWindowWidth=800;
		
		listPartServiceController = ManualVoucherOrderDTOController.class.getName();
		listPartJsController = ManualVoucherOrderDTOController.class.getName();
		listPartImportJs = "/gsb/bd/js/u8/manualvoucherorder.part.js";
		listToolbarPath="/basic/u8/manualvoucherorder/toolbar";
	}
	
	@Test
	public void createToolbar() {
		ResourceNode node = this.getResourceNode();
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("手动凭证订单工具栏");
			toolbar.setResourceNode(node);
		}
		addToolbarItem(toolbar, "disabled", "新增凭证跟进记录", "fa-stop-circle-o","bindReceiptWeb();", null, 5);
		addToolbarItem(toolbar, "disabled", "查看凭证跟进记录", "fa-stop-circle-o","bindReceiptWeb();", null, 5);
		toolbarService.save(toolbar);
	}

	//默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("手动凭证订单列表");
		}
		
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "订单id", ControlTypes.TEXT_BOX, 100);{
			column.setVisible(false);
		}
		addColumn(datagrid, "orderNo", "订单编号", ControlTypes.TEXT_BOX, 100);
	    addColumn(datagrid, "operator", "业务员", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "payablePrice", "订单金额", ControlTypes.DECIMAL_BOX, 80);
		addColumn(datagrid, "paidPrice", "订单已支付金额", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "custName", "客户名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "isManualVoucher", "手动原因", ControlTypes.ENUM_BOX, 300);
		column = addColumn(datagrid, "manualVoucherStatus", "凭证状态", ControlTypes.ENUM_BOX, 100);
		{
			column.setFormatter("return controllermanualVoucherOrderDTOList.changeManualVoucherStatusFormatter(value,row,index);");
		}
		addColumn(datagrid, "addTime", "订单创建日期", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "returnTime", "首款回款日期", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}


	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "orderNo", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "operator", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "custName", "客户名称", ControlTypes.NUMBER_BOX);
		addQueryItem(queryProject, "isManualVoucher", "手动原因", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "manualVoucherStatus", "凭证状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "addTime", "订单创建日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "returnTime", "首款回款日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	
	
	//默认的表单操作
	@Override
	protected void doOperation() {
		
		/*ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);*/
	}
}
