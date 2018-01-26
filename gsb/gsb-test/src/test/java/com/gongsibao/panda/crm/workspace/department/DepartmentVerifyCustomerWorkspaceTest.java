package com.gongsibao.panda.crm.workspace.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.CustomerVerifyListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.crm.workspace.salesman.SalesmanVerifyCustomerWorkspaceTest;

public class DepartmentVerifyCustomerWorkspaceTest extends SalesmanVerifyCustomerWorkspaceTest{

	@Before
	public void setup() {

		entity = NCustomer.class;
		urlList = "/crm/department/customer/verify";
		listToolbarPath = "/crm/department/customer/verify/edit";
		listPartName = formPartName = "校验客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_DEPARTMENT_TASK_ADD";
		listPartImportJs = "/gsb/crm/platform/js/customer-verify-list.part.js";
		listPartJsController = CustomerVerifyListPart.class.getName();
		listPartServiceController = CustomerVerifyListPart.class.getName();
	}
}
