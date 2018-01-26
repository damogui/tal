package com.gongsibao.panda.crm.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.util.StringManager;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.panda.operation.workspace.crm.CustomerALLWorkspaceTest;

public class AllSalesmanCustomerWorkspaceTest extends CustomerALLWorkspaceTest{
	
	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/salesman/customer/list";
		listToolbarPath = "crm/salesman/customer/edit";
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/crm/base/js/customer-base-list.part.js");
		ss.add("/gsb/crm/salesman/js/customer-all-list.part.js");
		listPartImportJs = StringManager.join("|", ss);

		listPartJsController = NCustomerAllListPart.class.getName();
		listPartServiceController = NCustomerAllListPart.class.getName();

		//listFilter = "ownerId = '{userId}' and id in (select min(id) from n_crm_customer_task group by customer_id)";
	}
}
