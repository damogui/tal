package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.igirl.ic.web.*;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**
 * @ClassName:  ProductWorkspaceTest
 * @Description:TODO 地市分类
 * @author: 曹玉玺
 * @date:   2018.4.3
 *
 */
public class IcExRegisterCaseWorkspaceTest extends WorkspaceCreationBase{

	public static final String trademarkToolbarPath = "/igirl/tm/toolbar";
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/ic/IcExRegisterCase/all/list";
		urlForm = "/igirl/IcExRegisterCase/form";
		entity = IcExRegisterCase.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_STATE_IcExRegisterCase";
		formPartName = listPartName = meta.getName();
		formJsImport = "/gsb/igirl/js/icexregistercase.form.part.js";
		formServiceController = IcExRegisterCasePart.class.getName();
		formJsController = IcExRegisterCasePart.class.getName();
		listToolbarPath="/igirl/state/IcExRegisterCase/list";
		listPartImportJs = "/gsb/igirl/js/icexregistercase.list.part.js";
		listPartServiceController = IcExRegisterCaseListPart.class.getName();
		listPartJsController = IcExRegisterCaseListPart.class.getName();
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
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("编辑");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}
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
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("doAllot");
			item.setIcon("fa fa-link");
			item.setName("分配");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.doAllot();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icFormToolbarPath);
			toolbar.setName("上传附件工具栏");
			toolbar.setResourceNode(node);

		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("save");
			item.setIcon("fa fa-save");
			item.setName("保存");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.save();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}


		toolbarService.save(toolbar);
	}

	/*首页显示表格*/
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "customer.realName", "客户姓名", ControlTypes.TEXT_BOX, 300);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "customer.mobile", "客户电话", ControlTypes.TEXT_BOX, 300, true);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "approvalName", "核准公司名称", ControlTypes.TEXT_BOX, 300);
		column.setAlign(DatagridAlign.CENTER);
		column = addColumn(datagrid, "approvalType", "审核状态", ControlTypes.ENUM_BOX, 300);
		column = addColumn(datagrid, "corpRegStatue", "工商业务状态", ControlTypes.ENUM_BOX, 300);
		column = addColumn(datagrid, "operator", "操作者", ControlTypes.ENUM_BOX, 300);
		return datagrid;
	}

	/*新增页显示表格*/
	/*新增页第一页效果*/
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		PFormField formField = null;
		formField = addFormField(form, "customerMobile", "客户电话", null, ControlTypes.TEXT_BOX, true,false);
		{
			formField.setTroikaTrigger("controllericExRegisterCase.isTel(this);");
		}
		addFormField(form, "customerName", "客户姓名", null, ControlTypes.TEXT_BOX, true,false);
		formField = addFormField(form, "approvalName", "核准公司名称", null, ControlTypes.TEXT_BOX, true,false);
		{
			formField.setTroikaTrigger("controllericExRegisterCase.isCom(this);");
		}
		addFormField(form, "approvalType", "审核状态", null, ControlTypes.ENUM_BOX, true,false);
		addFormField(form, "corpRegStatue", "工商业务状态", null, ControlTypes.ENUM_BOX, true,false);
		addFormField(form, "operator", "操作者", null, ControlTypes.ENUM_BOX, true,false);
		addFormField(form, "tokenImgUrl", "二维码", null, ControlTypes.IMAGE, false, true);
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "approvalName", "核准公司名称", ControlTypes.TEXT_BOX);
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


