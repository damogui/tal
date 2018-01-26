package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerAllocatedListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerAllocatedWorkspaceTest extends CustomerALLWorkspaceTest {

	@Before
	public void setup() {

		super.setup();
		
		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/allocated/list";// 列表的url
		listPartName = formPartName = "未分配客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_Allocated";
		listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/platform/js/customer-allocated-list.part.js";
		listFilter="";//未分配客户
		listPartJsController = NCustomerAllocatedListPart.class.getName();
		listPartServiceController = NCustomerAllocatedListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {
		
	}

}
