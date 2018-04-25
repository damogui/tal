package com.gongsibao.panda.action.supplier;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.action.supplier.salesman.SalesmanActionAddTest;
import com.gongsibao.panda.action.supplier.salesman.SalesmanActionDeleteTest;
import com.gongsibao.panda.action.supplier.salesman.SalesmanActionUpdateTest;
import com.gongsibao.panda.action.supplier.supplier.SupplierActionCloseTest;
import com.gongsibao.panda.action.supplier.supplier.SupplierActionOpenTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	
	SalesmanActionAddTest.class, 
	SalesmanActionUpdateTest.class,
	SalesmanActionDeleteTest.class,
	SupplierActionCloseTest.class, 
	SupplierActionOpenTest.class
})
	
public class SupplierActionTest {

}
