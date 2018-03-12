package com.gongsibao.panda.platform.product;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.product.workspace.OnSaleProductWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.PriceAuditWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.ProductPackageWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.ProductProjectWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.ProductPutawayWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.ProductSoldOutWorkspaceTest;
import com.gongsibao.panda.platform.product.workspace.ProductWorkspaceTest;

@RunWith(Suite.class)
@SuiteClasses({

	ResourceTest.class, 

	ProductWorkspaceTest.class,
	ProductProjectWorkspaceTest.class,
	OnSaleProductWorkspaceTest.class,
	PriceAuditWorkspaceTest.class,
	ProductPackageWorkspaceTest.class,
	ProductPutawayWorkspaceTest.class,
	ProductSoldOutWorkspaceTest.class,
	

	NavigationTest.class
})
public class AllTest {

}
