package com.gongsibao.franchisee;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "招商CRM";
		this.treePath = "panda/gsb/franchisee";
		this.resourceNode = "GSB_BD";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_BD", "招商CRM", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "GSB_BD_MY", "供应商管理", "", 1);
		{
			createPTreeNode(tree, "GSB_BD_MY", null, "BD_MY_UN_TRACK", "待跟进", "/bd/franchisee/my/untrack/list", 1);
			createPTreeNode(tree, "GSB_BD_MY", null, "BD_MY_MY", "供应商列表", "/bd/franchisee/my/list", 2);
			createPTreeNode(tree, "GSB_BD_MY", null, "BD_WORKBENCH", "工作台", "/bd/my/workbench", 3);
		}

		createPTreeNode(tree, null, null, "GSB_BD_DEPARTMENT", "部门统计", "", 2);
		{
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Franchisee", "供应商列表", "/bd/department/franchisee/list", 1);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Franchisee_Track", "跟进列表", "/bd/department/franchisee/track/list", 2);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Franchisee_UnTrack", "未跟进供应商", "/bd/department/franchisee/untrack/list", 3);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Franchisee_Track_Report", "跟进统计", "/bd/department/track/report", 4);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Day_Report", "日统计", "/bd/department/day/report", 5);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Week_Report", "周统计", "/bd/department/week/report", 6);
			createPTreeNode(tree, "GSB_BD_DEPARTMENT", null, "BD_DEPARTMENT_Month_Report", "月统计", "/bd/department/month/report", 7);
		}
		

		createPTreeNode(tree, null, null, "GSB_BD_OPERATION", "运营统计", "", 3);
		{
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Franchisee", "供应商列表", "/bd/operation/franchisee/list", 1);
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Track", "跟进列表", "/bd/operation/franchisee/track/list", 2);
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_UnTrack", "未跟进供应商", "/bd/operation/franchisee/untrack/list", 3);
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Track_Report", "跟进统计", "/bd/operation/track/report", 4);
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Day_Report", "日统计", "/bd/operation/day/report", 5);			
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Month_Report", "月统计", "/bd/operation/month/report", 6);
			createPTreeNode(tree, "GSB_BD_OPERATION", null, "GSB_BD_OPERATION_Year_Report", "年统计", "/bd/operation/year/report", 7);
		}
	}
}