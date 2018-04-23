package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.igirl.ic.web.IcExLogListPart;
import com.gongsibao.igirl.ic.web.IcExLogPart;
import com.gongsibao.igirl.ic.web.IcExRegisterCaseListPart;
import com.gongsibao.igirl.ic.web.IcExRegisterCasePart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @ClassName:  ProductWorkspaceTest
 * @Description:TODO 地市分类
 * @author: 曹玉玺
 * @date:   2018.4.3
 *
 */
public class IcExLogWorkspaceTest extends WorkspaceCreationBase{

	public static final String trademarkToolbarPath = "/igirl/tm/toolbar";
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/ic/IcExLog/all/list";
		urlForm = "/igirl/IcExLog/form";
		entity = IcExLog.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_STATE_IcExLog";
		listPartName = meta.getName();
		listToolbarPath="/igirl/state/IcExLog/list";
		listPartImportJs = "/gsb/igirl/js/icexlog.list.part.js";
		listPartServiceController = IcExLogListPart.class.getName();
		listPartJsController = IcExLogListPart.class.getName();
	}

	public static final String icFormToolbarPath = "/igirl/ic/toolbar";

	/*按钮*/
	@Test
	public void fromToolbar() {

		ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		PToolbarItem item = new PToolbarItem();
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	/*首页显示表格*/
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "title", "标题", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "content", "内容", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "companyName", "公司名称", ControlTypes.TEXT_BOX, 300);
		column = addColumn(datagrid, "corpRegStatue", "状态", ControlTypes.ENUM_BOX, 300);
		return datagrid;
	}



	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "title", "标题", ControlTypes.TEXT_BOX);
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


