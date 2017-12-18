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

		String parentNodeCode = ResourceTest.resourcePrefix + "_Perfrmance";
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "部门业绩统计", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Department_Day", "日统计", "/report/perfrmance/department/day", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Department_Week", "周统计", "/report/perfrmance/department/week", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Department_Month", "月统计", "/report/perfrmance/department/month", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Department_Season", "季统计", "/report/perfrmance/department/season", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Department_Year", "年统计", "/report/perfrmance/department/year", 5);
		}
		
		createPTreeNode(tree, null, "fa fa-bar-chart-o fa-fw", parentNodeCode, "业务员业绩统计", "", 2);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Salesman_Day", "日统计", "/report/perfrmance/salesman/day", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Salesman_Week", "周统计", "/report/perfrmance/salesman/week", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Salesman_Month", "月统计", "/report/perfrmance/salesman/month", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Salesman_Season", "季统计", "/report/perfrmance/salesman/season", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Salesman_Year", "年统计", "/report/perfrmance/salesman/year", 5);
		}
	}
}

