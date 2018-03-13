package com.gongsibao.panda.platform.operation;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.cms.ProductView;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerOrder;
import com.gongsibao.entity.crm.CustomerServiceConfig;
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
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier_Category", "服务商分类", "/operation/supplier/category/list", 3);
            createPTreeNode(tree, "GSB_Supplier", null, "GSB_Operation_Supplier", "服务商列表", "/operation/supplier/list", 4);
        }
        createPTreeNode(tree, null,null, "GSB_CRM_Manager", "客户管理（旧）", "", 4);
        {
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_All_" + Customer.class.getSimpleName(), "全部客户", "/crm/customer/all/list", 1);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_My_" + Customer.class.getSimpleName(), "我的客户", "/crm/customer/my/list", 2);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CompanyIntention.class.getSimpleName(), "企业信息库", "/crm/company/list", 3);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Pool_" + Customer.class.getSimpleName(), "客户池", "/crm/customer/pool/list", 4);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_Operation_" + Customer.class.getSimpleName(), "客户操作", "/crm/customer/operation/list", 5);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerServiceConfig.class.getSimpleName(), "客服配置", "/crm/customer/service/config/list", 6);
            createPTreeNode(tree, "GSB_CRM_Manager", null, "CRM_" + CustomerOrder.class.getSimpleName(), "订单列表", "/crm/customer/order/list", 7);
        }
        createPTreeNode(tree, null,null, "Operation_CRM", "客户管理", "", 5);
        {
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Add", "新增客户", "/crm/platform/customer/add", 1);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_ALL", "全部客户", "/operation/customer/all/list", 2);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Allocated", "已分配客户", "/operation/customer/allocated/list", 3);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_Undistributed", "未分配客户", "/operation/customer/undistributed/list", 4);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_NotTask", "无任务客户", "/operation/customer/nottask/list", 4);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_ALL", "全部任务", "/operation/customer/task/all/list", 5);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Allocated", "已分配任务", "/operation/customer/task/allocated/list", 6);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Undistributed", "未分配任务", "/operation/customer/task/undistributed/list", 7);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_UNstart", "未启动任务", "/operation/customer/task/unstart/list", 8);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Defeated", "无法签单", "/operation/customer/task/defeated/list", 9);
            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Task_Anomaly_Detection", "抽查异常", "/operation/customer/task/anomalydetection/list", 10);

            createPTreeNode(tree, "Operation_CRM", null, "Operation_CRM_Customer_OpenSea", "公海", "/operation/task/opensea/list", 11);
        }

        createPTreeNode(tree, null,null, "Operation_IGIRL", "智能商标", "", 6);
        {
            createPTreeNode(tree, "Operation_IGIRL", null, "Operation_IGIRL_All_TradeMarkCase", "申请方案列表", "/operation/igirl/trademarkcase/all/list", 1);

            createPTreeNode(tree, "Operation_IGIRL", null, "Operation_IGIRL_All_TradeMark", "申请进度跟进", "/operation/igirl/all/progress/list", 2);
         }


        createPTreeNode(tree, null, null, "Operation_Order", "订单管理", "", 7);
        {
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_All", "全部订单", "/operation/order/all/list", 1);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Pool", "订单池", "/operation/order/pool/list", 2);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Performance", "订单业绩", "/operation/order/performance/list", 3);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Received", "回款业绩", "/operation/order/received/list", 4);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Refund", "退款订单", "/operation/order/refund/list", 5);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Staging", "分期订单", "/operation/order/staging/list", 6);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Carryover", "结转订单", "/operation/order/carryover/list", 7);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Contract", "合同管理", "/operation/order/contract/list", 8);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Invoice", "发票管理", "/operation/order/invoice/list", 9);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Day_Report", "日统计", "/operation/order/report/day", 10);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Week_Report", "周统计", "/operation/order/report/week", 11);
            createPTreeNode(tree, "Operation_Order", null, "Operation_Order_Month_Report", "月统计", "/operation/order/report/month", 12);            
        }
    }
}