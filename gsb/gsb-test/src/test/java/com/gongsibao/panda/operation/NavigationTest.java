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
import com.gongsibao.entity.taurus.UserConsStatisticView;
import com.gongsibao.entity.taurus.UserConsumptionView;
import com.gongsibao.entity.taurus.UserInfo;
import com.gongsibao.entity.taurus.UserRenewalStatisticView;
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
		
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_WANDA", "万达项目", "", 1);
		{
			createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + ProductView.class.getSimpleName(), "服务列表", "/operation/wanda/product/list", 1);
			createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + SoOrder.class.getSimpleName(), "订单列表", "/operation/wanda/order/list", 1);
		}
	}
}
