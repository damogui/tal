package com.gongsibao.panda.supplier.crm;

import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;

public class CrmNavigationTest extends NavigationBase{

	public void createAccodions() {

		//this.doCreateAccodions("GSB_CRM", "客户管理", "fa fa-users fa-fw", 3);
	}

	protected void createPTree() {

		PNavigation tree = treeService.byPath("panda/gsb/supplier");
		this.doCreateTree(tree);
		grantPermission(tree);
		treeService.save(tree);
	}
	
	@Override
	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url, int seq) {

		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setCode(code);
			node.setName(name);
			node.setUrl(url);
			node.setParent(parentCode);
			node.setSeq(seq);
			node.setIcon(icon);
			node.setPathId(tree.getId());
			tree.getTreeNodes().add(node);
		}

		return node;
	}

	@Override
	protected void doCreateTree(PNavigation tree) {
		
		String nodeIcon="fa fa-circle-o";
		createPTreeNode(tree, null, "fa fa-user-o", "GSB_CRM", "客户管理", "", 1);
		{

			createPTreeNode(tree, "GSB_CRM", null, "CRM_DEPARTMENT", "部门管理", "", 1);
			{
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_CUSTOMER_ADD", "新增客户", "/crm/department/customer/add", 1);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_CUSTOMER_ALL", "全部客户", "/crm/department/customer/list", 2);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_ALL", "全部商机", "/crm/department/task/all/list", 3);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_START", "未启动商机", "/crm/department/unstart/list", 4);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_FOLLOWING", "跟进中商机", "/crm/department/following/list", 5);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_SIGNED", "已签单商机", "/crm/department/signed/list", 6);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_UNFOOLOW", "待跟进商机", "/crm/department/unfoolow/list", 7);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_TIMEOUT", "超时提醒", "/crm/department/task/timeout/list", 8);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_DEFEATED", "无法签单", "/crm/department/defeated/list", 9);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_CHECK_ABNORMAL", "抽查异常", "/crm/department/check/abnormal/list", 10);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_HIGHSEAS", "公海", "/crm/department/highseas/list", 11);
				createPTreeNode(tree, "CRM_DEPARTMENT", nodeIcon, "CRM_DEPARTMENT_TASK_Follow", "跟进列表", "/crm/department/task/follow/list", 12);
			}
			
			createPTreeNode(tree, "GSB_CRM", null, "CRM_STATISTICAL", "统计分析", "", 2);
			{
				createPTreeNode(tree, "CRM_STATISTICAL", nodeIcon, "CRM_STATISTICAL_COMPREHEN", "综合统计", "/crm/statistical/comprehen/list", 1);
				createPTreeNode(tree, "CRM_STATISTICAL", nodeIcon, "CRM_STATISTICAL_FUNNEL", "漏斗统计", "/crm/statistical/customer/funnel/list", 2);
				createPTreeNode(tree, "CRM_STATISTICAL", nodeIcon, "CRM_STATISTICAL_FOLLOW", "跟进统计", "/crm/statistical/follow/list", 3);
				//createPTreeNode(tree, "CRM_STATISTICAL", null, "CRM_STATISTICAL_CUSTOMERSERVICE", "客服统计", "/crm/statistical/customer/list", 4);
			}

			createPTreeNode(tree, "GSB_CRM", null, "CRM_SALESMAN", "我的商机", "", 3);
			{
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_CUSTOMER_ADD", "新增客户", "/crm/salesman/customer/add", 1);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_CUSTOMER", "全部客户", "/crm/salesman/customer/list", 2);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_ALL", "全部商机", "/crm/salesman/task/all/list", 3);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_START", "未启动商机", "/crm/salesman/task/unstart/list", 4);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_FOLLOWING", "跟进中商机", "/crm/salesman/task/following/list", 5);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_SIGNED", "已签单商机", "/crm/salesman/task/signed/list", 6);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_UNFOOLOW", "待跟进商机", "/crm/salesman/task/unfoolow/list", 7);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_DEFEATED", "无法签单商机", "/crm/salesman/task/defeated/list", 8);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_CHECK_ABNORMAL", "抽查异常", "/crm/salesman/check/abnormal/list", 9);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TIMEOUT", "超时提醒", "/crm/salesman/task/timeout/list", 10);
				createPTreeNode(tree, "CRM_SALESMAN", nodeIcon, "CRM_SALESMAN_TASK_Follow", "我的跟进", "/crm/salesman/task/follow/list", 11);
			}
		}

	}
}
