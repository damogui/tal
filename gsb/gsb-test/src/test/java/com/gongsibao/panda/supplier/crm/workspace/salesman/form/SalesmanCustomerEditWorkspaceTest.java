package com.gongsibao.panda.supplier.crm.workspace.salesman.form;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.platform.operation.workspace.crm.form.CustomerEditWorkspaceTest;

public class SalesmanCustomerEditWorkspaceTest extends CustomerEditWorkspaceTest{

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
		ss.add("/gsb/supplier/crm/base/js/customer-base-form.part.js");
		ss.add("/gsb/supplier/crm/salesman/js/customer-edit-form.part.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		
		formJsController = "com.gongsibao.crm.web.NCustomerSalesmanEditFormPart";
		formServiceController = NCustomerFormPart.class.getName();
		
		taskDetailResourceNodeCode = "CRM_SALESMAN_TASK_ALL";
		
		productsDetailResourceNodeCode = "CRM_SALESMAN_Products";
		foolowDetailResourceNodeCode = "CRM_SALESMAN_Foolow";
		notifyDetailResourceNodeCode = "CRM_SALESMAN_Notify";
		changeDetailResourceNodeCode = "CRM_SALESMAN_Change";
		companysResourceNodeCode = "CRM_SALESMAN_Companys";
		
		taskDetailJsController = "com.gongsibao.crm.web.SalesmanTaskDetailPart";
	}
	
	@Test
	public void createFormToolbar() {
		
	}
}
