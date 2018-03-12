package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerVerifyListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerVerifyWorkspaceTest;

public class SalesmanVerifyCustomerWorkspaceTest extends CustomerVerifyWorkspaceTest{

	@Before
	public void setup() {

		entity = NCustomer.class;
		urlList = "/crm/salesman/customer/verify";
		listToolbarPath = "/crm/my/customer/verify/edit";
		listPartName = formPartName = "校验客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER_ADD";
		listPartImportJs = "/gsb/platform/operation/crm/js/customer-verify-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = NCustomerVerifyListPart.class.getName();
		listPartServiceController = NCustomerVerifyListPart.class.getName();
	}
	
	@Test
	public void createRowToolbar() {
		
	}
}
