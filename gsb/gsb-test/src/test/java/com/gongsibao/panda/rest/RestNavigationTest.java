package com.gongsibao.panda.rest;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class RestNavigationTest extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "Rest接口";
		this.treePath = "panda/gsb/rest";
		this.resourceNode = "Gsb_Rest";
	}

	public void createAccodions() {
	}
	
	@Override
	protected void doCreateTree(PNavigation tree) {
	}
}
