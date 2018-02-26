package com.gongsibao.panda.supplier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.supplier.sys.SysAllTest;


@RunWith(Suite.class)
@SuiteClasses({ 
		SupplierResourceTest.class,
		SupplierNavigationTest.class,
		SupplierWorkbenchTest.class,
		com.gongsibao.panda.crm.AllTest.class,
		com.gongsibao.panda.igirl.AllTest.class,
		SysAllTest.class,
		})
		
public class SupplierAllTest {

}
