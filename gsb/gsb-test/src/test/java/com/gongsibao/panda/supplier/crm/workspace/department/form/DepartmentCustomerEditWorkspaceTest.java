package com.gongsibao.panda.supplier.crm.workspace.department.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.platform.operation.workspace.crm.form.CustomerEditWorkspaceTest;

public class DepartmentCustomerEditWorkspaceTest  extends CustomerEditWorkspaceTest{
	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomer.class;
		urlForm = "/crm/department/customer/edit";
		listPartName = formPartName = "客户信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_Edit";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/supplier/crm/department/js/customer-edit-form.part.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		
		formJsController = "com.gongsibao.crm.web.NCustomerDepartmentEditFormPart";
		formServiceController = NCustomerFormPart.class.getName();
		
		taskDetailResourceNodeCode = "CRM_DEPARTMENT_TASK_ALL";
		
		productsDetailResourceNodeCode = "CRM_DEPARTMENT_Products";
		foolowDetailResourceNodeCode = "CRM_DEPARTMENT_Foolow";
		notifyDetailResourceNodeCode = "CRM_DEPARTMENT_Notify";
		changeDetailResourceNodeCode = "CRM_DEPARTMENT_Change";
		companysResourceNodeCode = "CRM_DEPARTMENT_Companys";
		
		taskDetailJsController = "com.gongsibao.crm.web.DepartmentTaskDetailPart";
	}
	
	@Test
	public void createFormToolbar() {
		
	}
	@Override
	protected void addDetailGridPart(PWorkspace workspace) {
		createTasksPart(workspace);
		createCompanysDetailPart(workspace);
	}
	// 重新父类客户商机
	public void createTasksPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(taskDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "商机信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			
			addColumn(datagrid, "id", "商机Id", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
			addColumn(datagrid, "creator", "创建人", ControlTypes.DATETIME_BOX, 100);			
			addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "department.name", "分配部门", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "owner.name", "分配业务员", ControlTypes.TEXT_BOX, 100, false);			
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("商机信息");
			part.setCode("tasks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tasks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			
			part.setJsController(taskDetailJsController);
		}
		workspace.getParts().add(part);
		
		part = workspace.getParts().get(0);
		{
			part.setName("新增客户");
			part.setDockStyle(DockType.TOP);
			part.setHeight(500);
		}
	}
}
