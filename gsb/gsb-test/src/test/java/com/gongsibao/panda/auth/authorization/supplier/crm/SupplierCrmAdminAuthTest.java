package com.gongsibao.panda.auth.authorization.supplier.crm;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/*crm Amdin*/
public class SupplierCrmAdminAuthTest extends AuthBaseTest {
	
	@Before
	public void setup() {

		roleCode = "Supplier_Admin";
		super.setup();
	}

	protected void getResourceCodeList() {

		this.resourceNodeCodeList.add("GSB_CRM");
		this.resourceNodeCodeList.add("GSB_CRM_SYS");
//        this.resourceNodeCodeList.add("GSB_CRM_MY");//客户管理 我的商机
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Salesman");//我的订单
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Department");//部门订单
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Refund");//退款审核
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Staging");//分期审核
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Order");//订单审核
        this.resourceNodeCodeList.add("Gsb_Supplier_Order_Audit_Contract");//合同审核：
        this.resourceNodeCodeList.add("GSB_CRM_STATISTICAL");//统计分析：
		this.resourceNodeCodeList.add ("ChangePassword");//密码权限
		
	}
}
