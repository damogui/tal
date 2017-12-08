package com.gongsibao.u8;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;

public class NavigationTest extends NavigationBase {

	//初始化导航信息
	@Before
	public void setup() {
		this.treeName = "U8管理";
		this.treePath = "panda/gsb/u8";
		this.resourceNode = "GSB_U8";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_U8", "U8管理", "fa fa-users fa-fw", 1);//顶部和右侧顶部（配置菜单名称和图标、个数等）
	}

	//创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_U8_Manage", "U8管理", "", 1);//一级菜单
		{
			createPTreeNode(tree, "GSB_U8_Manage", null, "U8_" + SetOfBooks.class.getSimpleName(), "账套列表", "/u8/setofBbooks/list", 1);//二级菜单
			createPTreeNode(tree, "GSB_U8_Manage", null, "U8_" + U8Bank.class.getSimpleName(), "科目银行列表", "/u8/bank/list", 1);//二级菜单
			createPTreeNode(tree, "GSB_U8_Manage", null, "U8_" + VoucherLog.class.getSimpleName(), "凭证日志记录", "/u8/voucherLog/list", 1);//二级菜单
		}
	}
}