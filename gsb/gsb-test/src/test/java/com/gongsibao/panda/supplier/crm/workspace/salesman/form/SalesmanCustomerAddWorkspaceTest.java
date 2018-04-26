package com.gongsibao.panda.supplier.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.platform.operation.workspace.crm.form.CustomerAddWorkspaceTest;
/*业务员新增客户*/
public class SalesmanCustomerAddWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/salesman/customer/add";
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_ADD";

		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/supplier/crm/salesman/js/customer-add-form.part.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.SalesmanNCustomerAddFormPart";
		taskDetailResourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		
		taskDetailJsController = "com.gongsibao.crm.web.SalesmanNCustomerTaskDetailPart";
		
		companysResourceNodeCode = "CRM_SALESMAN_Companys";
	}
	
	@Test
	public void createFormToolbar() {
		
	}
	
	// 客户商机
	public void createTasksPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode(taskDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "商机信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			
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
