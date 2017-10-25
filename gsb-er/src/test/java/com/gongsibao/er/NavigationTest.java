package com.gongsibao.er;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.er.Order;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "任务管理";
		this.treePath = "panda/gsb/er";
		this.resourceNode = "GSB_ER";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_ER", "任务管理", "fa fa-tasks fa-fw", 2);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-tasks fa-fw", "ER_Task", "任务管理", "", 1);
		{
			createPTreeNode(tree, "ER_Task", null, "ER_Task_1_" + Order.class.getSimpleName(), "指挥中心", "/er/task/1/list", 1);
			createPTreeNode(tree, "ER_Task", null, "ER_Task_2_" + Order.class.getSimpleName(), "全部订单", "/er/task/2/list", 2);
			createPTreeNode(tree, "ER_Task", null, "ER_Task_3_" + Order.class.getSimpleName(), "我的任务", "/er/task/3/list", 3);
			createPTreeNode(tree, "ER_Task", null, "ER_Task_4_" + Order.class.getSimpleName(), "待审核", "/er/task/4/list", 4);
		}

		createPTreeNode(tree, null, "fa fa-cog fa-fw", "ER_Config", "配置管理", "", 2);
		{
			createPTreeNode(tree, "ER_Config", null, "ER_Config_1_" + Order.class.getSimpleName(), "流程管理", "/er/config/1/list", 1);
			createPTreeNode(tree, "ER_Config", null, "ER_Config_2_" + Order.class.getSimpleName(), "模版管理", "/er/config/2/list", 2);
			createPTreeNode(tree, "ER_Config", null, "ER_Config_3_" + Order.class.getSimpleName(), "客户管理", "/er/config/3/list", 3);
			createPTreeNode(tree, "ER_Config", null, "ER_Config_4_" + Order.class.getSimpleName(), "商品管理", "/er/config/4/list", 4);
			createPTreeNode(tree, "ER_Config", null, "ER_Config_5_" + Order.class.getSimpleName(), "任务设置", "/er/config/5/list", 5);
		}
	}
}
