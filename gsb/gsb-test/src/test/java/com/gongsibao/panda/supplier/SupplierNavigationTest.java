package com.gongsibao.panda.supplier;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class SupplierNavigationTest extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "服务商系统";
		this.treePath = "panda/gsb/supplier";
		this.resourceNode = "Gsb_Supplier_System";
	}

	public void createAccodions() {

		this.doCreateAccodions("Gsb_Supplier_System", "服务商系统", "fa fa-file fa-fw", 0);
	}
	
	@Override
	protected void doCreateTree(PNavigation tree) {
		
	}
}
