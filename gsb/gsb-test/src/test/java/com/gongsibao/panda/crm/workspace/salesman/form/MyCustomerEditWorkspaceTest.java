package com.gongsibao.panda.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerEditFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.operation.workspace.crm.form.CustomerEditWorkspaceTest;

public class MyCustomerEditWorkspaceTest extends CustomerEditWorkspaceTest{

	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomer.class;
		urlForm = "/crm/salesman/customer/edit";
		listPartName = formPartName = "客户信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_Edit";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/customer-add-form.part.js");
		ss.add("/gsb/crm/platform/js/customer-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		formJsController = NCustomerEditFormPart.class.getName();
		formServiceController = NCustomerEditFormPart.class.getName();
		
		taskDetailResourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		
		productsDetailResourceNodeCode = "CRM_SALESMAN_Manager_Products";
		foolowDetailResourceNodeCode = "CRM_SALESMAN_Manager_Foolow";
		notifyDetailResourceNodeCode = "CRM_SALESMAN_Manager_Notify";
		changeDetailResourceNodeCode = "CRM_SALESMAN_Manager_Change";
	}
}
