package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerUndistributedListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerUndistributedWorkspaceTest extends CustomerALLWorkspaceTest {
	
	@Before
	public void setup() {

		super.setup();
		
		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/undistributed/list";// 列表的url
		listPartName = formPartName = "未分配客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_Undistributed";
		listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/platform/js/customer-undistributed-list.part.js";
		
		listFilter = "id in (SELECT customer_id from n_crm_customer_task where owner_id is null or owner_id=0)";
		
		listPartJsController = NCustomerUndistributedListPart.class.getName();
		listPartServiceController = NCustomerUndistributedListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {
		
	}
}