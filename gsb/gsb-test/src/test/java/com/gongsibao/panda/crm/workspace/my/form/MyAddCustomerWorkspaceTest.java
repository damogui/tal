package com.gongsibao.panda.crm.workspace.my.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.util.StringManager;

import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;

public class MyAddCustomerWorkspaceTest extends CustomerAddWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/salesman/customer/add";
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_ADD";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/customer-add-form.part.js");
		ss.add("/gsb/crm/salesman/js/customer-add-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.MyNCustomerAddFormPart";
		taskDetailResourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		
		taskDetailJsController = "com.gongsibao.crm.web.MyNCustomerTaskDetailPart";
	}
}
