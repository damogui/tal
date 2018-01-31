package com.gongsibao.panda.operation;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.cms.ProductView;
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;
import com.gongsibao.entity.taurus.NewUserPerDayView;
import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserConsumptionView;
import com.gongsibao.entity.taurus.UserInfo;
import com.gongsibao.entity.trade.SoOrder;

public class NavigationTest extends NavigationBase {
	@Before
	public void setup() {
		this.treeName = "运营管理";
		this.treePath = "panda/gsb/operation";
		this.resourceNode = "GSB_OPERATION";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_OPERATION", "运营管理", "fa fa-file fa-fw", 0);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_TAURUS", "金牛座", "", 1);
		{
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + User.class.getSimpleName(), "帐号信息", "/taurus/user/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + UserInfo.class.getSimpleName(), "用户信息", "/taurus/userinfo/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + NewUserPerDayView.class.getSimpleName(), "每日新增用户数", "/taurus/user/perDay/list", 1);
//			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + UserConsStatisticView.class.getSimpleName(), "消费统计", "/taurus/user/consStatistic/list", 1);
//			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + UserRenewalStatisticView.class.getSimpleName(), "续费统计", "/taurus/user/renewalStatistic/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + ActiveUserView.class.getSimpleName(), "活跃度", "/taurus/user/active/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + DayStatisticView.class.getSimpleName(), "日统计数据", "/taurus/user/dayStatistic/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + JnzUserBehaviorStatistics.class.getSimpleName(), "统计数据", "/taurus/user/statistic/list", 1);
			createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + UserConsumptionView.class.getSimpleName(), "用户消费数据", "/taurus/user/userConsumption/list", 1);
		}
		
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_WANDA", "万达项目", "", 2);
		{
			createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + ProductView.class.getSimpleName(), "服务列表", "/operation/wanda/product/list", 1);
			createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + SoOrder.class.getSimpleName(), "订单列表", "/operation/wanda/order/list", 1);
		}
		
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_Supplier", "服务商管理", "", 3);
		{			
			createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Customer_Quality", "客户质量", "/operation/customer/quality/list", 1);
			createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Function_Module", "功能模块", "/operation/supplier/module/list", 2);
			createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier_Category", "服务商分类", "/operation/supplier/category/list",3);
			createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier", "服务商列表", "/operation/supplier/list", 4);
		}
		
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "Operation_CRM", "客户管理", "", 4);
		{
			createPTreeNode(tree, "Operation_CRM", "fa fa-user-plus fa-fw", "Operation_CRM_Customer_Add", "新增客户", "/crm/platform/customer/add", 1);
			
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_ALL", "全部客户", "/operation/customer/all/list", 2);
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Allocated", "已分配客户", "/operation/customer/allocated/list", 3);
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Undistributed", "未分配客户", "/operation/customer/undistributed/list", 4);
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_NotTask", "无任务客户", "/operation/customer/nottask/list", 4);
			
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_ALL", "全部任务", "/operation/customer/task/all/list", 5);
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Allocated", "已分配任务", "/operation/customer/task/allocated/list", 6);
			//createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Undistributed", "未分配任务", "/operation/customer/task/undistributed/list", 7);
			
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Defeated", "无法签单", "/operation/customer/task/defeated/list", 8);
			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Anomaly_Detection", "抽查异常", "/operation/customer/task/anomalydetection/list", 9);

			createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_OpenSea", "公海", "/operation/task/opensea/list", 10);
		}
	}
}
