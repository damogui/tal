package com.gongsibao.panda.supplier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.crm.CrmAllTest;
import com.gongsibao.panda.supplier.igirl.IgirlAllTest;
import com.gongsibao.panda.supplier.order.OrderAllTest;
import com.gongsibao.panda.supplier.sys.SysAllTest;


@RunWith(Suite.class)
@SuiteClasses({ 
		SupplierResourceTest.class,
		SupplierNavigationTest.class,
		SupplierWorkbenchTest.class,
		CrmAllTest.class,
		OrderAllTest.class,
		IgirlAllTest.class,
		SysAllTest.class,
		})
		
public class SupplierAllTest {

}
