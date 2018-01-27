package com.gongsibao.panda.crm.workspace.department.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerEditFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.operation.workspace.crm.form.CustomerEditWorkspaceTest;

public class DepartmentCustomerEditWorkspaceTest  extends CustomerEditWorkspaceTest{
	@Before
	public void setup() {
		
		super.setup();
		entity = NCustomer.class;
		urlForm = "/crm/department/customer/edit";
		listPartName = formPartName = "客户信息";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_Edit";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/platform/js/customer-add-form.part.js");
		ss.add("/gsb/crm/platform/js/customer-edit-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);

		formJsController = NCustomerEditFormPart.class.getName();
		formServiceController = NCustomerEditFormPart.class.getName();
		
		taskDetailResourceNodeCode = "CRM_DEPARTMENT_TASK_ALL";
		
		productsDetailResourceNodeCode = "CRM_DEPARTMENT_Products";
		foolowDetailResourceNodeCode = "CRM_DEPARTMENT_Foolow";
		notifyDetailResourceNodeCode = "CRM_DEPARTMENT_Notify";
		changeDetailResourceNodeCode = "CRM_DEPARTMENT_Change";
	}
}
