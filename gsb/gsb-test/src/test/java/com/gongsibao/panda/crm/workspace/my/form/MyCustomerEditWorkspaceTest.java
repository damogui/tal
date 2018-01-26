package com.gongsibao.panda.crm.workspace.my.form;

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
		resourceNodeCode = "GSB_CRM_MY_CUSTOMER_Edit";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/customer-add-form.part.js");
		ss.add("/gsb/crm/platform/js/customer-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		formJsController = NCustomerEditFormPart.class.getName();
		formServiceController = NCustomerEditFormPart.class.getName();
		
		taskDetailResourceNodeCode = "GSB_CRM_MY_TASK_ALL";
		
		productsDetailResourceNodeCode = "GSB_CRM_My_Manager_Products";
		foolowDetailResourceNodeCode = "GSB_CRM_My_Manager_Foolow";
		notifyDetailResourceNodeCode = "GSB_CRM_My_Manager_Notify";
		changeDetailResourceNodeCode = "GSB_CRM_My_Manager_Change";
	}
}
