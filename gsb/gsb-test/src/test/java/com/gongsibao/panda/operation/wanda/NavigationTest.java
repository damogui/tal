package com.gongsibao.panda.operation.wanda;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.cms.ProductView;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "服务管理";
		this.treePath = "panda/gsb/cms";
		this.resourceNode = "GSB_CMS";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_CMS", "服务管理", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_CMS_Manager", "服务管理", "", 1);
		{
			createPTreeNode(tree, "GSB_CMS_Manager", null, "CMS_" + ProductView.class.getSimpleName(), "服务列表", "/cms/product/list", 1);
		}
	}
}
