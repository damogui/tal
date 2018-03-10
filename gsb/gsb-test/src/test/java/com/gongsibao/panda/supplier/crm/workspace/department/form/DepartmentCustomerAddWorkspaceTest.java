package com.gongsibao.panda.supplier.crm.workspace.department.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class DepartmentCustomerAddWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/department/customer/add";
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_ADD";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/supplier/crm/department/js/customer-add-form.part.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.DepartmentNCustomerAddFormPart";
		taskDetailResourceNodeCode = "CRM_DEPARTMENT_TASK_ALL";
		taskDetailJsController = "com.gongsibao.crm.web.DepartmentNCustomerTaskDetailPart";
		
		companysResourceNodeCode = "CRM_DEPARTMENT_Companys";
	}
	
	@Test
	public void createFormToolbar() {
		
	}
}
