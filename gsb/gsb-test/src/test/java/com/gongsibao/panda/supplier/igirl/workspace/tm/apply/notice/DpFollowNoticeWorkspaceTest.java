package com.gongsibao.panda.supplier.igirl.workspace.tm.apply.notice;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.igirl.tm.web.DepartmentTradeMarkListPart;
import com.gongsibao.igirl.tm.web.TradeMarkListPart;
import com.gongsibao.utils.SupplierSessionManager;

public class DpFollowNoticeWorkspaceTest extends WorkspaceCreationBase {
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/notice/dpfollow/list";
		urlForm = "/igirl/notice/dpfollow/form";
		entity = TradeMark.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGIRL_DpFollowNotice_TradeMark";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/notice/dpfollow/list";
		listPartServiceController = DepartmentTradeMarkListPart.class.getName();
		listPartJsController=TradeMarkListPart.class.getName();
		listPartImportJs="/gsb/igirl/js/abnormaltrademark.listpart.js";
		listFilter = "markState in ('6','7','13','15','17','18','20','21') ";
	}


	@Test
	public void fromToolbar() {

		ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			//toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("进度跟进工具栏");
			toolbar.setResourceNode(node);

		}
		PToolbarItem item = new PToolbarItem();
		{
			
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("autoSubmit");
			item.setIcon("fa fa-link");
			item.setName("审核");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.autoSubmit(1);");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("autoSubmit");
			item.setIcon("fa fa-link");
			item.setName("返回");
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.autoSubmit(2);");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("attachment");
			item.setIcon("fa fa-link");
			item.setName("回执");
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.attachment();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("状态");
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("exportExcel");
			item.setIcon("fa fa-download");
			item.setName("导出");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.exportExcel();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("商标大类");
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 120);
		addColumn(datagrid, "markSubmitTime", "商标提交时间", ControlTypes.DATETIME_BOX, 120);
		addColumn(datagrid, "tradeMarkCase.ownerName", "业务人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "nclOne.code", "商标大类", ControlTypes.TEXT_BOX, 50);
		addColumn(datagrid, "tradeMarkCase.orderCode", "订单号", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "code", "商标号", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "proxyCode", "代理号", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "tradeMarkCase.companyName", "公司名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "tradeMarkCase.applier", "申请人", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memo", "商标说明", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "markState", "状态", ControlTypes.ENUM_BOX, 100);
//		column = addColumn(datagrid, "markState", "状态", ControlTypes.ENUM_BOX, 0);
//		{
//			column.setFormatter("return controllertradeMarkList.filterMarkState();");
//		}
		addColumn(datagrid, "tradeMarkCaseId", "案件id", ControlTypes.TEXT_BOX, 200).setVisible(false);
		column = addColumn(datagrid, "tradeMarkCase.urgency", "紧急(小时)", ControlTypes.TEXT_BOX, 100);
		column.setOrderbyMode(OrderbyMode.ASC);
		column=addColumn(datagrid, "id","操作", ControlTypes.TEXT_BOX, 200);{
			column.setFormatter("return controllertradeMarkList.markPic(value,row,index);");
		}
		datagrid.setSortName("createTime Desc");
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		PFormField field = null;
		addFormField(form, "code", "商标号", null, ControlTypes.TEXT_BOX, false,false);
		addFormField(form, "markState", "申请状态", null, ControlTypes.ENUM_BOX, true,false);
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "商标号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "tradeMarkCase.orderCode", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "proxyCode", "代理号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "tradeMarkCase.companyName", "公司名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "tradeMarkCase.applier", "申请人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "tradeMarkCase.ownerName", "业务员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "markState", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "createTime", "时间", ControlTypes.DATE_BOX).setWidth(400);
		addQueryItem(queryProject, "markSubmitTime", "商标提交时间", ControlTypes.DATE_BOX).setWidth(400);
		//		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
		//			
		//			item.setCustomerControlType(DictComboBox.class.getName());
		//			item.setRefFilter("type=8");
		//		}
		//addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}


}
