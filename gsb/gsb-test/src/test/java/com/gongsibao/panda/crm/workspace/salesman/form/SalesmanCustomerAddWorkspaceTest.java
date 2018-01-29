package com.gongsibao.panda.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class SalesmanCustomerAddWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/salesman/customer/add";
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_ADD";

		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/crm/salesman/js/customer-add-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.SalesmanNCustomerAddFormPart";
		taskDetailResourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		
		taskDetailJsController = "com.gongsibao.crm.web.SalesmanNCustomerTaskDetailPart";
	}
	
	@Test
	public void createFormToolbar() {
		
	}
}
