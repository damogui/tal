package com.gongsibao.panda.crm.workspace.department.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.panda.operation.workspace.crm.form.TaskEditWorkspaceTest;

public class DepartmentTaskEditWorkspaceTest extends TaskEditWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/department/task/edit";
		listPartName = formPartName = "任务信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_DEPARTMENT_TASK_EDIT";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/task-add-form.part.js");
		ss.add("/gsb/crm/base/js/task-base-edit-form.part.js");
		ss.add("/gsb/crm/department/js/task-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		
		formJsController = NCustomerTaskEditFormPart.class.getName();
		formServiceController = NCustomerTaskEditFormPart.class.getName();
		
		productsDetailResourceNodeCode = "CRM_DEPARTMENT_Products";
		foolowDetailResourceNodeCode = "CRM_DEPARTMENT_Foolow";
		notifyDetailResourceNodeCode = "CRM_DEPARTMENT_Notify";
		changeDetailResourceNodeCode = "CRM_DEPARTMENT_Change";
		inspectionDetailResourceNodeCode = "CRM_DEPARTMENT_Inspection";
		taskFollowDetailPart = "com.gongsibao.crm.web.DepartmentTaskFollowDetailPart";
	}
	
	@Test
	public void detailPart() {
		
	}
}
