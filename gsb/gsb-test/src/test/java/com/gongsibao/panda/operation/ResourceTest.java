package com.gongsibao.panda.operation;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cms.base.IProductViewService;
import com.gongsibao.crm.base.INCustomerChangeService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.cms.ProductView;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerChange;
import com.gongsibao.entity.crm.NCustomerProduct;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.supplier.FunctionModule;
import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierCategory;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.supplier.SupplierFunctionModule;
import com.gongsibao.entity.supplier.SupplierServiceDistrict;
import com.gongsibao.entity.supplier.SupplierServiceProduct;
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;
import com.gongsibao.entity.taurus.NewUserPerDayView;
import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.entity.taurus.UserConsStatisticView;
import com.gongsibao.entity.taurus.UserConsumptionView;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.entity.taurus.UserInfo;
import com.gongsibao.entity.taurus.UserRenewalStatisticView;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.supplier.base.IFunctionModuleRoleService;
import com.gongsibao.supplier.base.IFunctionModuleService;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierCategoryService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierFunctionModuleService;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.supplier.base.ISupplierServiceDistrictService;
import com.gongsibao.supplier.base.ISupplierServiceProductService;
import com.gongsibao.taurus.base.IActiveUserViewService;
import com.gongsibao.taurus.base.IBdUserBehaviorStatistics;
import com.gongsibao.taurus.base.IDayStatisticViewService;
import com.gongsibao.taurus.base.INewUserPerDayViewService;
import com.gongsibao.taurus.base.IUserCollectCompanyService;
import com.gongsibao.taurus.base.IUserConsStatisticViewService;
import com.gongsibao.taurus.base.IUserConsumptionService;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;
import com.gongsibao.taurus.base.IUserInfoService;
import com.gongsibao.taurus.base.IUserRenewalStatisticViewService;
import com.gongsibao.taurus.base.IUserService;
import com.gongsibao.taurus.base.IUserWalletLogService;
import com.gongsibao.trade.base.IOrderService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "运营管理";
		parentNodeCode = "GSB_Operation";
		pluginName = "运营管理";
		seq = 5;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("金牛座", "GSB_TAURUS", node.getId());
		{
			this.createResourceNodeVoucher(User.class.getName(), "帐号信息", "GSB_TAURUS_" + User.class.getSimpleName(), IUserService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserInfo.class.getName(), "用户信息", "GSB_TAURUS_" + UserInfo.class.getSimpleName(), IUserInfoService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserWalletLog.class.getName(), "钱包记录", "GSB_TAURUS_" + UserWalletLog.class.getSimpleName(), IUserWalletLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserCollectCompany.class.getName(), "关注企业", "GSB_TAURUS_" + UserCollectCompany.class.getSimpleName(), IUserCollectCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserDingtalkKeyword.class.getName(), "舆情关键字", "GSB_TAURUS_" + UserDingtalkKeyword.class.getSimpleName(), IUserDingtalkKeywordService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NewUserPerDayView.class.getName(), "每日新增用户数", "GSB_TAURUS_" + NewUserPerDayView.class.getSimpleName(), INewUserPerDayViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserConsStatisticView.class.getName(), "用户消费统计", "GSB_TAURUS_" + UserConsStatisticView.class.getSimpleName(), IUserConsStatisticViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserRenewalStatisticView.class.getName(), "用户续费统计", "GSB_TAURUS_" + UserRenewalStatisticView.class.getSimpleName(), IUserRenewalStatisticViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ActiveUserView.class.getName(), "用户活跃度", "GSB_TAURUS_" + ActiveUserView.class.getSimpleName(), IActiveUserViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DayStatisticView.class.getName(), "日统计数据", "GSB_TAURUS_" + DayStatisticView.class.getSimpleName(), IDayStatisticViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(JnzUserBehaviorStatistics.class.getName(), "统计数据", "GSB_TAURUS_" + JnzUserBehaviorStatistics.class.getSimpleName(), IBdUserBehaviorStatistics.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserConsumptionView.class.getName(), "用户消费数据", "GSB_TAURUS_" + UserConsumptionView.class.getSimpleName(), IUserConsumptionService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("万达项目", "GSB_WANDA", node.getId());
		{
			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "GSB_WANDA_" + ProductView.class.getSimpleName(), IProductViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单列表", "GSB_WANDA_" + SoOrder.class.getSimpleName(), IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("服务商管理", "GSB_Supplier", node.getId());
		{
			this.createResourceNodeVoucher(SupplierCategory.class.getName(), "服务商分类", "GSB_Operation_Supplier_Category", ISupplierCategoryService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Supplier.class.getName(), "服务商列表", "GSB_Operation_Supplier", ISupplierService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierFunctionModule.class.getName(), "服务商开通模块", "GSB_Operation_Supplier_Function_Module", ISupplierFunctionModuleService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierServiceProduct.class.getName(), "服务商服务产品", "GSB_Operation_Supplier_Service_Product", ISupplierServiceProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierServiceDistrict.class.getName(), "服务商服务地区", "GSB_Operation_Supplier_Service_District", ISupplierServiceDistrictService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FunctionModule.class.getName(), "功能模块", "GSB_Operation_Function_Module", IFunctionModuleService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(FunctionModuleRole.class.getName(), "功能模块角色", "GSB_Operation_Supplier_Function_Module_Role", IFunctionModuleRoleService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierDepartment.class.getName(), "服务商部门", "GSB_Operation_Supplier_Department", ISupplierDepartmentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Salesman.class.getName(), "服务商业务员", "GSB_Operation_Supplier_Salesman", ISalesmanService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("客户管理", "Operation_CRM", node.getId());
		{
			this.createResourceNodeVoucher(NCustomer.class.getName(), "新增客户", "Operation_CRM_Customer_Add", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "修改客户", "Operation_CRM_Customer_Edit", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "新增任务", "Operation_CRM_Task_Add", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "修改任务", "Operation_CRM_Task_Edit", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "跟进", "Operation_CRM_Foolow_Edit", INCustomerTaskFoolowService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "公海", "Operation_CRM_Customer_OpenSea", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "全部客户", "Operation_CRM_Customer_ALL", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "已分配客户", "Operation_CRM_Customer_Allocated", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "未分配客户", "Operation_CRM_Customer_Undistributed", INCustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomer.class.getName(), "无任务客户", "Operation_CRM_Customer_NotTask", INCustomerService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "全部任务", "Operation_CRM_Task_ALL", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "已分配任务", "Operation_CRM_Task_Allocated", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "未分配任务", "Operation_CRM_Task_Undistributed", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "无法签单", "Operation_CRM_Task_Defeated", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTask.class.getName(), "抽查异常", "Operation_CRM_Task_Anomaly_Detection", INCustomerTaskService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskQuality.class.getName(), "客户质量", "Operation_CRM_Customer_Quality", INCustomerTaskQualityService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(NCustomerProduct.class.getName(), "客户意向产品", "Operation_CRM_Customer_Products", INCustomerProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskFoolow.class.getName(), "沟通日志", "Operation_CRM_Customer_Foolow",INCustomerTaskFoolowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskNotify.class.getName(), "通知日志", "Operation_CRM_Customer_Notify",INCustomerTaskNotifyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerChange.class.getName(), "流转日志", "Operation_CRM_Customer_Change",INCustomerChangeService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCustomerTaskInspection.class.getName(), "抽查日志", "Operation_CRM_Customer_Inspection",INCustomerTaskInspectionService.class.getName(), node1.getId());
		
		
		}

	}
}