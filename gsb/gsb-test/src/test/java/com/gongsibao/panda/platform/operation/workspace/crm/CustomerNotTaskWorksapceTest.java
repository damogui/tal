package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.crm.web.platform.PlatformCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.Important;

public class CustomerNotTaskWorksapceTest extends CustomerALLWorkspaceTest{

	
	@Before
	public void setup() {

		super.setup();
		
		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/nottask/list";// 列表的url
		listPartName = formPartName = "无商机客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_NotTask";
		listPartImportJs = "/gsb/supplier/crm/base/js/customer-base-list.part.js|/gsb/platform/operation/crm/js/customer-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		
		listFilter = "id not in (SELECT customer_id from n_crm_customer_task GROUP BY customer_id) and important <>" + Important.INVALID.getValue(); 
		
		listPartJsController = NCustomerAllListPart.class.getName();
		listPartServiceController = PlatformCustomerAllListPart.class.getName();
	}
	
	@Test
	public void createListToolbar() {
		
	}
}
