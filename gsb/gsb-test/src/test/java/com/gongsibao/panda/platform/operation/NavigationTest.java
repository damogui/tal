package com.gongsibao.panda.platform.operation;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.cms.ProductView;
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;
import com.gongsibao.entity.taurus.NewUserPerDayView;
import com.gongsibao.entity.taurus.UcOrganizationUserView;
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

        createPTreeNode(tree, null, null, "GSB_TAURUS", "金牛座", "", 1);
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
            createPTreeNode(tree, "GSB_TAURUS", null, "GSB_TAURUS_" + UcOrganizationUserView.class.getSimpleName(), "业务用户统计", "/taurus/user/userOrganization/list", 1);
        }

        createPTreeNode(tree, null, null, "GSB_WANDA", "万达项目", "", 2);
        {
            createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + ProductView.class.getSimpleName(), "服务列表", "/operation/wanda/product/list", 1);
            createPTreeNode(tree, "GSB_WANDA", null, "GSB_WANDA_" + SoOrder.class.getSimpleName(), "订单列表", "/operation/wanda/order/list", 1);
        }

        createPTreeNode(tree, null, null, "GSB_Supplier", "服务商管理", "", 3);
        {
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Customer_Quality", "客户质量", "/operation/customer/quality/list", 1);
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Function_Module", "功能模块", "/operation/supplier/module/list", 2);
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier_Category", "服务商分组", "/operation/supplier/category/list", 3);
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier", "服务商列表", "/operation/supplier/list", 4);
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier_ALL_Salesman", "业务员列表", "/operation/supplier/all/salesman/list", 5);
        }
//        createPTreeNode(tree, null, null, "GSB_CRM_Manager", "客户管理（旧）", "", 4);
//        {
//            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_All_" + Customer.class.getSimpleName(), "全部客户", "/crm/customer/all/list", 1);
//            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_My_" + Customer.class.getSimpleName(), "我的客户", "/crm/customer/my/list", 2);
//            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Pool_" + Customer.class.getSimpleName(), "客户池", "/crm/customer/pool/list", 4);
//            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Operation_" + Customer.class.getSimpleName(), "客户操作", "/crm/customer/operation/list", 5);
//            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerOrder.class.getSimpleName(), "订单列表", "/crm/customer/order/list", 7);
//        }
        createPTreeNode(tree, null, null, "Operation_CRM", "客户管理", "", 5);
        {
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Add", "新增客户", "/crm/platform/customer/add", 1);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_ALL", "全部客户", "/operation/customer/all/list", 2);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Allocated", "已分配客户", "/operation/customer/allocated/list", 3);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Undistributed", "未分配客户", "/operation/customer/undistributed/list", 4);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_NotTask", "无商机客户", "/operation/customer/nottask/list", 5);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Invalid", "无效客户", "/operation/customer/invalid/list", 6);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_ALL", "全部商机", "/operation/customer/task/all/list", 7);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Allocated", "已分配商机", "/operation/customer/task/allocated/list", 8);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Undistributed", "未分配商机", "/operation/customer/task/undistributed/list", 9);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_UNstart", "未启动商机", "/operation/customer/task/unstart/list", 10);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Defeated", "无法签单", "/operation/customer/task/defeated/list", 11);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Anomaly_Detection", "抽查异常", "/operation/customer/task/anomalydetection/list", 12);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_OpenSea", "公海", "/operation/task/opensea/list", 13);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Follow", "跟进列表", "/operation/customer/task/follow/list", 14);
            createPTreeNode(tree, "Operation_CRM", null, "CRM_CompanyIntention", "企业信息库", "/crm/company/list", 15);
            createPTreeNode(tree, "Operation_CRM", null, "CRM_CustomerServiceConfig", "客服配置", "/crm/customer/service/config/list", 16);
        }

        createPTreeNode(tree, null, null, "Operation_IGIRL", "智能商标", "", 6);
        {
            createPTreeNode(tree, "Operation_IGIRL", null, "Operation_IGIRL_All_TradeMarkCase", "申请方案列表", "/operation/igirl/trademarkcase/all/list", 1);

            createPTreeNode(tree, "Operation_IGIRL", null, "Operation_IGIRL_All_TradeMark", "申请进度跟进", "/operation/igirl/all/progress/list", 2);
        }

        createPTreeNode(tree, null, null, "Operation_CRM_STATISTICAL", "统计分析", "", 7);
        {
            createPTreeNode(tree, "Operation_CRM_STATISTICAL", null, "Operation_CRM_STATISTICAL_Comprehen", "综合统计", "/operation/statistical/comprehen/list", 1);
            createPTreeNode(tree, "Operation_CRM_STATISTICAL", null, "Operation_CRM_STATISTICAL_Funnel", "漏斗统计", "/operation/statistical/funnel/list", 2);
            createPTreeNode(tree, "Operation_CRM_STATISTICAL", null, "Operation_CRM_STATISTICAL_Follow", "跟进统计", "/operation/statistical/follow/list", 3);
            createPTreeNode(tree, "Operation_CRM_STATISTICAL", null, "Operation_CRM_STATISTICAL_CustomerService", "客服统计", "/operation/statistical/customer/list", 4);
        }

    }
}