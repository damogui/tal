package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerAllocatedWorkspaceTest extends CustomerALLWorkspaceTest {

	@Before
	public void setup() {

		super.setup();
		
		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/allocated/list";// 列表的url
		listPartName = formPartName = "已分配客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_Allocated";
		listPartImportJs = "/gsb/supplier/crm/base/js/customer-base-list.part.js|/gsb/platform/operation/crm/js/customer-all-list.part.js|/gsb/gsb.custom.query.controls.js";
		listFilter = "id in (SELECT customer_id from n_crm_customer_task where owner_id is not null and owner_id>0)";//已分配客户
		listPartJsController = NCustomerAllListPart.class.getName();
		listPartServiceController = NCustomerAllListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {
		
	}

}
