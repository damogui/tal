package com.gongsibao.panda.bd;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;


public class NavigationTest  extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "用户行为统计";
		this.treePath = "panda/gsb/user/behavior";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "用户行为统计", "fa fa-bar-chart fa-fw", 6);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_user";
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "用户行为统计", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_behavior", "用户行为统计", "/behavior/query", 1);
		}
	}
}

