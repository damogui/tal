package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class AddDepartmentCustomerWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/department/customer/add";
		resourceNodeCode = "GSB_CRM_DEPARTMENT_ADD";
		
		taskDetailResourceNodeCode = "GSB_CRM_DEPARTMENT_ALL";
	}
}
