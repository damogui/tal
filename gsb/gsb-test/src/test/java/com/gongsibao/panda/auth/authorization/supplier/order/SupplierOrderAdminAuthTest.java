package com.gongsibao.panda.auth.authorization.supplier.order;

import org.junit.Before;

import com.gongsibao.panda.auth.authorization.AuthBaseTest;

/*服务商管理员*/
public class SupplierOrderAdminAuthTest extends AuthBaseTest{
	
	@Before
	public void setup() {

		roleCode = "Supplier_Order_Admin";
		super.setup();
	}
	
	protected void getResourceCodeList() {
//客户管理（我的商机、部门商机）、订单管理（我的订单、部门订单）、系统设置、审核中心（退款审核、分期审核、订单审核、合同审核）、统计分析（综合统计、漏斗统计、跟进统计）
		//this.resourceNodeCodeList.add("Gsb_Supplier_Order");
		this.resourceNodeCodeList.add("GSB_CRM_SYS");//系统设置
        /*new*/
        this.resourceNodeCodeList.add("GSB_CRM_DEPARTMENT");//部门管理
        this.resourceNodeCodeList.add("GSB_CRM_MY");//客户管理 我的商机
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
