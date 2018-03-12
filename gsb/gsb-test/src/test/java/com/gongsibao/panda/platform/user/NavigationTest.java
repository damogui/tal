package com.gongsibao.panda.platform.user;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.uc.Account;
import com.gongsibao.entity.uc.Auth;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.entity.uc.Role;
import com.gongsibao.entity.uc.User;
import com.gongsibao.entity.uc.UserLoginLog;

public class NavigationTest extends NavigationBase{

	@Before
	public void setup() {
		this.treeName = "用户中心";
		this.treePath = "panda/gsb/user";
		this.resourceNode = "GSB_User_Center";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_User_Center", "用户中心", "fa fa-users fa-fw", 8);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "GSB_User", "用户中心", "", 1);
		{
			createPTreeNode(tree, "GSB_User", null, "User_Center_" + Organization.class.getSimpleName(), "组织设置", "/user/center/organization/list", 1);
			createPTreeNode(tree, "GSB_User", null, "User_Center_" + User.class.getSimpleName(), "员工列表", "/user/center/user/list", 2);
			createPTreeNode(tree, "GSB_User", null, "User_Center_" + Role.class.getSimpleName(), "角色列表", "/user/center/role/list", 3);
			createPTreeNode(tree, "GSB_User", null, "User_Center_" + Auth.class.getSimpleName(), "菜单管理", "/user/center/auth/list", 4);
			createPTreeNode(tree, "GSB_User", null, "User_Center_" + UserLoginLog.class.getSimpleName(), "登录日志", "/user/center/login/log/list", 5);
		}
		
		createPTreeNode(tree, null, null, "GSB_Account", "会员中心", "", 2);
		{
			createPTreeNode(tree, "GSB_Account", null, "Account_Center_" + Account.class.getSimpleName(), "会员列表", "/account/center/account/list", 1);
		}
	}
}