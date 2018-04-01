package com.gongsibao.panda.supplier.sys;

import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;

public class SysNavigationTest extends NavigationBase {

	public void createAccodions() {

	}
	
	protected void createPTree() {
		
		PNavigation tree = treeService.byPath("panda/gsb/supplier");
		this.doCreateTree(tree);
		grantPermission(tree);
		treeService.save(tree);
	}
	
	@Override
	protected PNavigationItem createPTreeNode(PNavigation tree, String parentCode,String icon, String code, String name, String url, int seq) {

		PNavigationItem node = new PNavigationItem();
		{
			node.toNew();
			node.setCode(code);
			node.setName(name);
			node.setUrl(url);
			node.setParent(parentCode);
			node.setSeq(seq);
			node.setIcon(icon);
			node.setPathId(tree.getId());
			tree.getTreeNodes().add(node);
		}

		return node;
	}
	
	@Override
	protected void doCreateTree(PNavigation tree) {
		String nodeIcon="fa fa-circle-o";
		createPTreeNode(tree, null, "fa fa-cog", "GSB_CRM_SYS", "系统设置", "", 100);
		{
			createPTreeNode(tree, "GSB_CRM_SYS", null, "GSB_CRM_SYS_Organization", "组织机构", "", 1);
			{
				createPTreeNode(tree, "GSB_CRM_SYS_Organization", nodeIcon, "GSB_CRM_SYS_DEPARTMENT", "部门列表", "/crm/sys/department/list", 1);
				createPTreeNode(tree, "GSB_CRM_SYS_Organization", nodeIcon, "GSB_CRM_SYS_SALESMAN", "员工列表", "/crm/sys/salesman/list", 2);
			}
		}
	}
}
