package com.gongsibao.panda.report;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;


public class NavigationTest  extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "报表中心";
		this.treePath = "panda/gsb/report";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "报表中心", "fa fa-bar-chart fa-fw", 6);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_Department_Perfrmance";
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "部门业绩", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Day", "日统计", "/report/perfrmance/department/day", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Week", "周统计", "/report/perfrmance/department/week", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Month", "月统计", "/report/perfrmance/department/month", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Season", "季统计", "/report/perfrmance/department/season", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Year", "年统计", "/report/perfrmance/department/year", 5);
		}
		
		parentNodeCode = ResourceTest.resourcePrefix + "_Salesman_Perfrmance";
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "业务员业绩", "", 2);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Day", "日统计", "/report/perfrmance/salesman/day", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Week", "周统计", "/report/perfrmance/salesman/week", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Month", "月统计", "/report/perfrmance/salesman/month", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Season", "季统计", "/report/perfrmance/salesman/season", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Year", "年统计", "/report/perfrmance/salesman/year", 5);
		}
		
		parentNodeCode = ResourceTest.resourcePrefix + "_Customer";
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "客户统计", "", 3);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Year", "年统计", "/report/customer/year", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Moth", "月统计", "/report/customer/month", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Week", "周统计", "/report/customer/week", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Day", "日统计", "/report/customer/day", 4);
//			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Product", "意向产品统计", "/report/customer/product", 5);
//			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_District", "意向地区统计", "/report/customer/district", 6);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Status", "客户状态统计", "/report/customer/status", 7);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Source", "客户来源统计", "/report/customer/source", 8);
		}
	}
}


