package com.gongsibao.panda.product;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.product.workspace.OnSaleProductWorkspaceTest;
import com.gongsibao.panda.product.workspace.PriceAuditWorkspaceTest;
import com.gongsibao.panda.product.workspace.ProductProjectWorkspaceTest;
import com.gongsibao.panda.product.workspace.ProductPutawayWorkspaceTest;
import com.gongsibao.panda.product.workspace.ProductSoldOutWorkspaceTest;
import com.gongsibao.panda.product.workspace.ProductWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	ProductWorkspaceTest.class,
	OnSaleProductWorkspaceTest.class,
	PriceAuditWorkspaceTest.class,
	ProductPutawayWorkspaceTest.class,
	ProductSoldOutWorkspaceTest.class,
	ProductProjectWorkspaceTest.class,
	ProductWorkspaceTest.class,
	
	NavigationTest.class
	
})

public class AllTest {

}
