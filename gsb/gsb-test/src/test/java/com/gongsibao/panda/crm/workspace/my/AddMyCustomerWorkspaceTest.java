package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class AddMyCustomerWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/my/customer/add";
		resourceNodeCode = "GSB_CRM_MY_CUSTOMER_ADD";
		
		taskDetailResourceNodeCode = "GSB_CRM_MY_TASK_ALL";
	}
}
