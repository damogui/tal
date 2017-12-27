package com.gongsibao.panda.basic.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.Dict;

public class DictWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = Dict.class;// 实体
		urlList = "/basic/config/dict/list";
		urlForm = "/basic/config/dict/form";
		listPartName = formPartName = "字典信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_Config_" + Dict.class.getSimpleName();

		formOpenMode = OpenMode.WINDOW;
		openWindowHeight = 400;
		openWindowWidth = 800;
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("字典信息列表");
		}

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "pid", "父id", ControlTypes.NUMBER_BOX, 150);
		addColumn(datagrid, "alias", "别名", ControlTypes.TEXT_BOX, 50);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 50);
		addColumn(datagrid, "enabled", "是否启用", ControlTypes.BOOLCOMBO_BOX, 50);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "类型", ControlTypes.ENUM_BOX);
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "type", "类型", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "alias", "别名", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "enabled", "是否启用", null, ControlTypes.SWITCH_BUTTON, true, false);

		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}