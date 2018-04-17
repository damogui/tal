package com.gongsibao.panda.auth.authorization.supplier.igirl;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

public class SupplierIgirlAdminAuthTest extends AuthBaseTest{
	@Before
	public void setup() {

		roleCode = "IGIRL_Admin";
		super.setup();
	}
	
	protected void getResourceCodeList() {
		this.resourceNodeCodeList.add("GSB_TRADE_AI");//智能商标
		this.resourceNodeCodeList.add("GSB_CRM_SYS");//系统设置
		this.resourceNodeCodeList.add("GSB_IC_AI");//智能工商
		
		//不要加这2个, hw 2018-04-16
//		this.resourceNodeCodeList.add("GSB_CRM");//客户管理
//		this.resourceNodeCodeList.add("Gsb_Supplier_Order");//订单管理
	}
}
