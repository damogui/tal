package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerNotTaskWorksapceTest extends CustomerALLWorkspaceTest{

	
	@Before
	public void setup() {

		super.setup();
		
		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/nottask/list";// 列表的url
		listPartName = formPartName = "无任务客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_NotTask";
		listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/platform/js/customer-all-list.part.js|/gsb/gsb.custom.query.controls.js";
		
		listFilter = "id not in (SELECT customer_id from n_crm_customer_task GROUP BY customer_id)"; 
		
		listPartJsController = NCustomerAllListPart.class.getName();
		listPartServiceController = NCustomerAllListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {
		
	}
}
