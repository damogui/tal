package com.gongsibao.panda.operation;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cms.base.IProductViewService;
import com.gongsibao.entity.cms.ProductView;
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.entity.taurus.NewUserPerDayView;
import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.entity.taurus.UserConsStatisticView;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.entity.taurus.UserRenewalStatisticView;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.taurus.base.IActiveUserViewService;
import com.gongsibao.taurus.base.IDayStatisticViewService;
import com.gongsibao.taurus.base.INewUserPerDayViewService;
import com.gongsibao.taurus.base.IUserCollectCompanyService;
import com.gongsibao.taurus.base.IUserConsStatisticViewService;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;
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
			this.createResourceNodeVoucher(User.class.getName(), "全部客户", "GSB_TAURUS_" + User.class.getSimpleName(), IUserService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserWalletLog.class.getName(), "钱包记录", "GSB_TAURUS_" + UserWalletLog.class.getSimpleName(), IUserWalletLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserCollectCompany.class.getName(), "关注企业", "GSB_TAURUS_" + UserCollectCompany.class.getSimpleName(), IUserCollectCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserDingtalkKeyword.class.getName(), "舆情关键字", "GSB_TAURUS_" + UserDingtalkKeyword.class.getSimpleName(), IUserDingtalkKeywordService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(NewUserPerDayView.class.getName(), "每日新增用户数", "GSB_TAURUS_" + NewUserPerDayView.class.getSimpleName(),INewUserPerDayViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserConsStatisticView.class.getName(), "用户消费统计", "GSB_TAURUS_" + UserConsStatisticView.class.getSimpleName(),IUserConsStatisticViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UserRenewalStatisticView.class.getName(), "用户续费统计", "GSB_TAURUS_" + UserRenewalStatisticView.class.getSimpleName(),IUserRenewalStatisticViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ActiveUserView.class.getName(), "用户活跃度", "GSB_TAURUS_" + ActiveUserView.class.getSimpleName(),IActiveUserViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DayStatisticView.class.getName(), "日统计数据", "GSB_TAURUS_" + DayStatisticView.class.getSimpleName(),IDayStatisticViewService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("万达项目", "GSB_WANDA", node.getId());
		{
			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "GSB_WANDA_" + ProductView.class.getSimpleName(),IProductViewService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单列表", "GSB_WANDA_" + SoOrder.class.getSimpleName(),IOrderService.class.getName(), node1.getId());
		}

//		node1 = this.createResourceNodeCategory("ICompany", "GSB_WANDA", node.getId());
//		{
//			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "GSB_WANDA" + ProductView.class.getSimpleName(),IProductViewService.class.getName(), node1.getId());
//		}
//		
//		node1 = this.createResourceNodeCategory("供应商管理", "GSB_WANDA", node.getId());
//		{
//			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "GSB_WANDA" + ProductView.class.getSimpleName(),IProductViewService.class.getName(), node1.getId());
//		}
//	
//		node1 = this.createResourceNodeCategory("网站CMS", "GSB_WANDA", node.getId());
//		{
//			this.createResourceNodeVoucher(ProductView.class.getName(), "服务列表", "GSB_WANDA" + ProductView.class.getSimpleName(),IProductViewService.class.getName(), node1.getId());
//		}
	}
}