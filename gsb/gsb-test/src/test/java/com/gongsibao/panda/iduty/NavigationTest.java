package com.gongsibao.panda.iduty;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "任务管理";
		this.treePath = "panda/gsb/iduty";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "任务管理", "fa fa-tasks fa-fw", 2);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_Task";
		createPTreeNode(tree, null, "fa fa-tasks fa-fw", parentNodeCode, "任务管理", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Command_Center", "指挥中心", "/iduty/task/command/center", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_All_Order", "全部订单", "/iduty/task/all/order/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_My_Task", "我的任务", "/iduty/task/my/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Await_Order", "待审核", "/iduty/task/await/order/list", 4);
		}

		parentNodeCode = ResourceTest.resourcePrefix + "_Config";
		createPTreeNode(tree, null, "fa fa-cog fa-fw", parentNodeCode, "配置管理", "", 2);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Flow", "流程管理", "/iduty/config/flow/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Template", "模版管理", "/iduty/config/template/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Customer", "客户管理", "/iduty/config/customer/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Product", "商品管理", "/iduty/config/product/list", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Task", "任务设置", "/iduty/config/task/list", 5);
		}

	}
}
