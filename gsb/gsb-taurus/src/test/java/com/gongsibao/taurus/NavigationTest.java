package com.gongsibao.taurus;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.taurus.User;

public class NavigationTest extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "运营管理";
		this.treePath = "panda/gsb/operation";
		this.resourceNode = "GSB_OPERATION";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_OPERATION", "运营管理", "fa fa-users fa-fw", 0);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_TAURUS", "金牛座", "", 1);
		{
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + User.class.getSimpleName(), "用户管理", "/taurus/user/list", 1);
		}
	}
}
