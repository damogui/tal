package com.gongsibao.panda.crm.workspace.department.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class DepartmentAddCustomerWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/department/customer/add";
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_ADD";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/crm/department/js/customer-add-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.DepartmentNCustomerAddFormPart";
		taskDetailResourceNodeCode = "CRM_DEPARTMENT_TASK_ALL";
		taskDetailJsController = "com.gongsibao.crm.web.DepartmentNCustomerTaskDetailPart";
	}
}
