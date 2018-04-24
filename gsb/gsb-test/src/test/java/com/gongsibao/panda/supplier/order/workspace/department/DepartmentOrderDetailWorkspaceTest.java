package com.gongsibao.panda.supplier.order.workspace.department;

import org.junit.Before;

import com.gongsibao.panda.supplier.order.workspace.salesman.SalesmanOrderDetailWorkspaceTest;

/**
 * 部门管理/全部明细订单
 * @author Administrator
 *
 */
public class DepartmentOrderDetailWorkspaceTest extends SalesmanOrderDetailWorkspaceTest{
	@Before
	public void setup() {
		super.setup();
		urlList = "/crm/order/department/detail/list";		
		resourceNodeCode = "Gsb_Supplier_Order_Department_OrderProd";
		listFilter = "department_id in (SELECT department_id FROM sp_salesman WHERE employee_id = '{userId}') ";
	}
}
