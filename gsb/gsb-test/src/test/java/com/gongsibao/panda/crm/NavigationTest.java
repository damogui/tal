package com.gongsibao.panda.crm;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerOrder;
import com.gongsibao.entity.crm.CustomerServiceConfig;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "客户管理";
		this.treePath = "panda/gsb/crm";
		this.resourceNode = "GSB_CRM";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_CRM", "客户管理", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_CRM_Manager", "客户管理", "", 1);
		{
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_All_" + Customer.class.getSimpleName(), "全部客户", "/crm/customer/all/list", 1);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_My_" + Customer.class.getSimpleName(), "我的客户", "/crm/customer/my/list", 2);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CompanyIntention.class.getSimpleName(), "企业信息库", "/crm/company/list", 3);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Pool_" + Customer.class.getSimpleName(), "客户池", "/crm/customer/pool/list", 4);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Operation_" + Customer.class.getSimpleName(), "客户操作", "/crm/customer/operation/list", 5);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerServiceConfig.class.getSimpleName(), "客服配置", "/crm/customer/service/config/list", 6);
			createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerOrder.class.getSimpleName(), "订单列表", "/crm/customer/order/list", 7);
		}

		createPTreeNode(tree, null, null, "CRM_SALESMAN", "我的任务", "", 2);
		{
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_CUSTOMER_ADD", "新增客户", "/crm/salesman/customer/add", 1);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_CUSTOMER", "全部客户", "/crm/salesman/customer/list", 2);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_ALL", "全部任务", "/crm/salesman/task/all/list", 3);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_START", "未启动任务", "/crm/salesman/task/unstart/list", 4);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_FOLLOWING", "跟进中任务", "/crm/salesman/task/following/list", 5);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_SIGNED", "已签单任务", "/crm/salesman/task/signed/list", 6);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_UNFOOLOW", "待跟进任务", "/crm/salesman/task/unfoolow/list", 7);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TASK_DEFEATED", "无法签单任务", "/crm/salesman/task/defeated/list", 8);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_CHECK_ABNORMAL", "抽查异常", "/crm/salesman/check/abnormal/list", 9);
			createPTreeNode(tree, "CRM_SALESMAN", null, "CRM_SALESMAN_TIMEOUT", "超时提醒", "/crm/salesman/task/timeout/list", 10);
		}

		createPTreeNode(tree, null, null, "GSB_CRM_DEPARTMENT", "部门管理", "", 3);
		{
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_ADD", "新增客户", "/crm/department/customer/add", 1);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_ALL", "全部任务", "/crm/department/2/list", 1);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_START", "未启动", "/crm/department/3/list", 2);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_UNFOOLOW", "待跟进", "/crm/department/4/list", 3);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_FOLLOWING", "跟进中", "/crm/department/5/list", 4);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_SIGNED", "已签单", "/crm/department/6/list", 5);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_DEFEATED", "无法签单", "/crm/department/7/list", 6);
			createPTreeNode(tree, "GSB_CRM_DEPARTMENT", null, "GSB_CRM_DEPARTMENT_HIGHSEAS", "公海", "/crm/department/8/list", 7);
		}
		//
		// createPTreeNode(tree, null, null, "GSB_CRM_REPORT", "分析统计", "", 4);
		// {
		// createPTreeNode(tree, "GSB_CRM_REPORT", null, "GSB_CRM_REPORT_1",
		// "报表1", "/crm/report/1/list", 1);
		// createPTreeNode(tree, "GSB_CRM_REPORT", null, "GSB_CRM_REPORT_2",
		// "报表2", "/crm/report/2/list", 2);
		// }
		//
		createPTreeNode(tree, null, null, "GSB_CRM_SYS", "系统设置", "", 5);
		{
			createPTreeNode(tree, "GSB_CRM_SYS", null, "GSB_CRM_SYS_DEPARTMENT", "部门列表", "/crm/sys/department/list", 1);
			createPTreeNode(tree, "GSB_CRM_SYS", null, "GSB_CRM_SYS_SALESMAN", "员工列表", "/crm/sys/salesman/list", 2);
		}
	}
}
