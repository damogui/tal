package com.gongsibao.bd;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;


public class NavigationTest extends NavigationBase {

	//初始化导航信息
	@Before
	public void setup() {
		this.treeName = "配置管理";
		this.treePath = "panda/gsb/config";
		this.resourceNode = "GSB_Config";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_Config", "配置管理", "fa fa-users fa-fw", 1);//顶部和右侧顶部（配置菜单名称和图标、个数等）
	}

	//创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_Config_Manage", "配置管理", "", 1);//一级菜单
		{
			createPTreeNode(tree, "GSB_Config_Manage", null, "Config_" + Dict.class.getSimpleName(), "字典列表", "/config/dict/list", 1);//二级菜单
		}
	}
}
