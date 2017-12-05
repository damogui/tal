package com.gongsibao.uc;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.Auth;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.entity.uc.Role;
import com.gongsibao.entity.uc.User;
import com.gongsibao.entity.uc.UserBusiness;
import com.gongsibao.entity.uc.UserLoginLog;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.entity.uc.UserRoleMap;
import com.gongsibao.uc.base.IAuthService;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.uc.base.IRoleService;
import com.gongsibao.uc.base.IUserBusinessService;
import com.gongsibao.uc.base.IUserLoginLogService;
import com.gongsibao.uc.base.IUserOrganizationMapService;
import com.gongsibao.uc.base.IUserRoleMapService;
import com.gongsibao.uc.base.IUserService;

public class ResourceTest  extends ResourceCreationBase{

	@Before
	public void setup() {

		parentNodeName = "用户中心";
		parentNodeCode = "GSB_User_Center";
		pluginName = "用户中心";
		seq = 8;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("用户中心", "GSB_User", node.getId());
		{
			this.createResourceNodeVoucher(Organization.class.getName(), "组织设置", "User_Center_" + Organization.class.getSimpleName(), IOrganizationService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(User.class.getName(), "员工列表", "User_Center_" + User.class.getSimpleName(), IUserService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(UserBusiness.class.getName(), "归属事业部", "User_Center_" + UserBusiness.class.getSimpleName(), IUserBusinessService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(UserRoleMap.class.getName(), "角色", "User_Center_" + UserRoleMap.class.getSimpleName(), IUserRoleMapService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(UserOrganizationMap.class.getName(), "所属组织", "User_Center_" + UserOrganizationMap.class.getSimpleName(), IUserOrganizationMapService.class.getName(), node1.getId());
		
			this.createResourceNodeVoucher(Role.class.getName(), "角色列表", "User_Center_" + Role.class.getSimpleName(), IRoleService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(Auth.class.getName(), "菜单管理", "User_Center_" + Auth.class.getSimpleName(), IAuthService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(UserLoginLog.class.getName(), "登录日志", "User_Center_" + UserLoginLog.class.getSimpleName(), IUserLoginLogService.class.getName(), node1.getId());
		}
	}
}
