package com.gongsibao.panda.platform.basic.workspace.u8;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;

public class SetOfBooksWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = SetOfBooks.class;// 实体
		urlList = "/basic/u8/setofBbooks/list";// 列表的url
		urlForm = "/basic/u8/setofBbooks/form";// 弹出框的url
		listPartName = formPartName = "账套信息";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + SetOfBooks.class.getSimpleName();// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowWidth = 800;
		openWindowHeight = 450;
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("账套信息列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "senderNo", "外部系统编号", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 100);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "taxRate", "税率", ControlTypes.DECIMAL_BOX, 100);
		{
			column.setPrecision(4);
		}
		addColumn(datagrid, "sort", "排序编号", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "accountCode", "科目编码", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "enterId", "默认的制单人id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "enterName", "默认的制单人", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "abbreviation", "简称", ControlTypes.TEXT_BOX, 200);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "isCreateVoucher", "是否生成凭证", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "enabled", "是否可用", ControlTypes.BOOLCOMBO_BOX, 50);
		addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 20);
		return datagrid;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "senderNo", "外部系统编号", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "type", "类型", null, ControlTypes.ENUM_BOX, true, false);
		PFormField field = addFormField(form, "taxRate", "税率", null, ControlTypes.DECIMAL_BOX, true, false);
		{
			field.setPrecision(4);
		}
		addFormField(form, "sort", "排序编号", null, ControlTypes.NUMBER_BOX, true, false);
		addFormField(form, "accountCode", "科目编码", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "enterId", "默认的制单人id", null, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "enterName", "默认的制单人", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "abbreviation", "简称", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "isCreateVoucher", "是否生成凭证", null, ControlTypes.SWITCH_BUTTON, true, false);
		addFormField(form, "enabled", "是否可用", null, ControlTypes.SWITCH_BUTTON, true, false);
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
