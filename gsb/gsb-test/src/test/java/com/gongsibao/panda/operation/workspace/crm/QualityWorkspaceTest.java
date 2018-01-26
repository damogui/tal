package com.gongsibao.panda.operation.workspace.crm;

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
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.NCustomerTaskQuality;

public class QualityWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = NCustomerTaskQuality.class;// 实体
		urlList = "/operation/customer/quality/list";// 列表的url
		urlForm = "/operation/customer/quality/form";// 弹出框的url
		listPartName = formPartName = "客户质量信息";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_Quality";

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowWidth = 800;
		openWindowHeight = 550;
		// formJsImport = "/gsb/gsb.customer.controls.js";
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("客户质量列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "intentionCategory", "大类", ControlTypes.ENUM_BOX, 100);
		column = addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 80);{
			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "nextFoolowDateRequired", "下次跟进时间必填", ControlTypes.BOOLCOMBO_BOX, 120);
		addColumn(datagrid, "requiredInfo", "必填信息", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "nextFoolowType", "下次跟进方式", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "code", "编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX);
		// 参照
		addRefrenceQueryItem(queryProject, "supplier.name", "服务商名称", "CRM_Supplier");
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);

		//PFormField field = null;
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "code", "编号", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "nextFoolowDateRequired", "下次跟进时间必填", ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "requiredInfo", "必填信息", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "nextFoolowType", "下次跟进方式", ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "memoto", "备注", ControlTypes.TEXT_BOX, false, false);

		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
