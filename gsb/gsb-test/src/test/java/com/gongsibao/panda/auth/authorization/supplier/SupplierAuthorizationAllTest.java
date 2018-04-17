package com.gongsibao.panda.auth.authorization.supplier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.auth.authorization.supplier.crm.SupplierCrmAdminAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.crm.SupplierCrmLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.crm.SupplierCrmSalesmanAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.igirl.SupplierIgirlAdminAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.igirl.SupplierIgirlAuditAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.igirl.SupplierIgirlLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.igirl.SupplierIgirlSalesmanAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.order.SupplierOrderAdminAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.order.SupplierOrderLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.order.SupplierOrderOperatorAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.order.SupplierOrderOperatorLeaderAuthTest;
import com.gongsibao.panda.auth.authorization.supplier.order.SupplierOrderSalesmanAuthTest;

@RunWith(Suite.class)
@SuiteClasses({

	SupplierCrmAdminAuthTest.class,
	SupplierCrmLeaderAuthTest.class,
	SupplierCrmSalesmanAuthTest.class,

	SupplierOrderAdminAuthTest.class,
	SupplierOrderLeaderAuthTest.class,
	SupplierOrderSalesmanAuthTest.class,
	SupplierOrderOperatorLeaderAuthTest.class,
	SupplierOrderOperatorAuthTest.class,

	SupplierIgirlAdminAuthTest.class,
	SupplierIgirlLeaderAuthTest.class,
	SupplierIgirlSalesmanAuthTest.class,
	SupplierIgirlAuditAuthTest.class,
})
public class SupplierAuthorizationAllTest {

}
