package com.gongsibao.taurus;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserCollectCompany;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.taurus.base.IUserCollectCompanyService;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;
import com.gongsibao.taurus.base.IUserService;
import com.gongsibao.taurus.base.IUserWalletLogService;

public class ResourceTest extends ResourceCreationBase{

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "运营管理";
		parentNodeCode = "GSB_OPERATION";
		pluginName = "运营管理";
		seq = 0;
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
		}
	}
}

