package com.gongsibao.panda.user.workspace.user;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.Plugin;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.Auth;

public class AuthWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		super.setup();

		// 在子类中重定义
		urlList = "/user/center/auth/list";
		urlForm = "/user/center/auth/form";

		entity = Auth.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "User_Center_" + Auth.class.getSimpleName();
//		listPartType = PartType.TREEGRID_PART.getId();
		this.listToolbarPath = "panda/datagrid/edit";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "url", "路径", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "sref", "sref", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "flag", "标签", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "icon", "图标", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "isMenu", "是否菜单", ControlTypes.BOOLCOMBO_BOX, 80);

		column = addColumn(datagrid, "sort", "顺序", ControlTypes.TEXT_BOX, 60);
		{
			column.setOrderbyMode(OrderbyMode.ASC);
		}

		column = addColumn(datagrid, "enabled", "状态", ControlTypes.ENUM_BOX, 60);
		{

			column.setStyler("return row.enabled==false?'color:red;':'color:#5FB878;';");
			column.setFormatter(" return value==false?'停用':'启用';");
		}
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 80);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;
		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "resourceType", "类型", ControlTypes.ENUM_BOX, true, false);
		addFormFieldRefrence(form, "plugin.name", "插件", null, Plugin.class.getSimpleName(), false, false);

		addFormField(form, "parentId", "parentId", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "seq", "顺序", ControlTypes.NUMBER_BOX, true, false);

		formField = addFormField(form, "entityId", "entityId", ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "service", "service", ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "memoto", "备注", ControlTypes.EDITOR, false, false);
		{

			formField.setHeight(250);
			formField.setFullColumn(true);
		}
		return form;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}

}
