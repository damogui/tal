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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;

public class U8BankWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = U8Bank.class;//实体
		urlList = "/basic/u8/bank/list";//列表的url
		urlForm = "/basic/u8/bank/form";//弹出框的url
		listPartName = formPartName = "科目银行信息";
		meta = MtableManager.getMtable(entity);//获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + U8Bank.class.getSimpleName();//菜单节点码（名称）
		
		formOpenMode = OpenMode.WINDOW;//编辑框打开的形式
		openWindowWidth = 900;
		openWindowHeight = 750;		
		formJsImport = "/gsb/panda-extend/gsb.customer.controls.js";
	}

	//默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("账套信息列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "主键", ControlTypes.DECIMAL_BOX, 50);
		addColumn(datagrid, "name", "银行/科目名称", ControlTypes.TEXT_BOX, 200);
		//addColumn(datagrid, "no", "卡号", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "code", "科目编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "abbreviation", "简称", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "setOfBooks.name", "账套名称", ControlTypes.TEXT_BOX, 200);		
		column = addColumn(datagrid, "type", "类型", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "offlineWayType.name", "原付款方式", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "supplierId", "u8供应商id", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "prepaySubjectId", "预付或手续费id", ControlTypes.NUMBER_BOX, 100);
		//addColumn(datagrid, "prepaySubject.name", "预付科目", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "personnelId", "u8人员id", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "deptId", "u8部门id", ControlTypes.TEXT_BOX, 100);		
		column =addColumn(datagrid, "taxRate", "税率", ControlTypes.DECIMAL_BOX, 100);			
		{
			column.setPrecision(4);
		}
		addColumn(datagrid, "sort", "排序编号", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "enabled", "是否可用", ControlTypes.BOOLCOMBO_BOX, 50);
		addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "code", "科目编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "类型", ControlTypes.ENUM_BOX);
		// 参照
		addRefrenceQueryItem(queryProject, "setOfBooks.name", "账套", SetOfBooks.class.getSimpleName());
		return queryProject;
	}
	
	//默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);

		PFormField field =null;

		addFormField(form, "name", "银行/科目名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "no", "卡号", ControlTypes.TEXT_BOX,  false, false);
		addFormField(form, "code", "科目编号", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "abbreviation", "简称", ControlTypes.TEXT_BOX,  false, false);
		addFormFieldRefrence(form, "setOfBooks.name", "账套名称",null,  SetOfBooks.class.getSimpleName(), false, false);
		addFormField(form, "type", "类型", ControlTypes.ENUM_BOX, true, false);		
		addFormField(form, "supplierId", "u8供应商id", ControlTypes.TEXT_BOX,  false, false);	
		addFormField(form, "prepaySubjectId", "预付或手续费id", ControlTypes.NUMBER_BOX,  false, false);
		//addFormFieldRefrence(form, "prepaySubject.name", "预付科目",null, U8Bank.class.getSimpleName(), false, false);
		addFormField(form, "personnelId", "u8人员id", ControlTypes.TEXT_BOX,  false, false);
		field = addFormField(form, "offlineWayType.name", "原线下付款方式", null, ControlTypes.CUSTOM, false, false);
		{
			field.setCustomControlType(DictComboBox.class.getName());
			field.setRefFilter("type=311");
		}
		addFormField(form, "deptId", "u8部门id", ControlTypes.TEXT_BOX, false, false);				
		field = addFormField(form, "taxRate", "税率", null, ControlTypes.DECIMAL_BOX, false, false);		{
			field.setPrecision(4);
		}
		addFormField(form, "sort", "排序编号", ControlTypes.NUMBER_BOX,  true, false);
		addFormField(form, "enabled", "是否可用", ControlTypes.SWITCH_BUTTON, false, false);
		
		
		return form;
	}
	
	//默认的表单操作
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		//operationService.addOperation(node,OperationTypes.delete);
	}
}
