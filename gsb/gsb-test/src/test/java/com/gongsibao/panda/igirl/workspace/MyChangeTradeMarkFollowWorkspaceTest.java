package com.gongsibao.panda.igirl.workspace;

import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.igirl.web.ChangeTradeMarkListPart;
import com.gongsibao.igirl.web.MyChangeTradeMarkListPart;
import com.gongsibao.igirl.web.TradeMarkListPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

public class MyChangeTradeMarkFollowWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/myctm/progress/list";
		urlForm = "/igirl/myctm/progress/form";

		entity = ChangeTradeMark.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGIRL_My_ChangeTradeMark";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/myctm/changeTradeMark/list";
		listPartServiceController = MyChangeTradeMarkListPart.class.getName();
		listPartJsController=ChangeTradeMarkListPart.class.getName();
		listPartImportJs="/gsb/igirl/js/changetrademark.listpart.js";
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
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("商标大类");
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(true);
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "createTime", "日期", ControlTypes.DATETIME_BOX, 100, true);
		addColumn(datagrid, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX, 120, true);
		addColumn(datagrid, "txt_sqrmyzw", "申请人名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid,"changeTradeMarkState","状态",ControlTypes.TEXT_BOX,100);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		PFormField field = null;
		addFormField(form, "agentFileNum", "商标号", null, ControlTypes.TEXT_BOX, false,false);
		addFormField(form, "changeTradeMarkState", "申请状态", null, ControlTypes.ENUM_BOX, true,false);
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "agentFileNum", "代理文号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "txt_sqrmyzw", "申请人名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "changeTradeMarkState", "状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "createTime", "时间", ControlTypes.DATE_BOX).setWidth(400);
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
