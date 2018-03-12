package com.gongsibao.panda.platform.product;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "产品管理";
		this.treePath = "panda/gsb/product";
		this.resourceNode = ResourceTest.resourcePrefix;
	}

	public void createAccodions() {

		this.doCreateAccodions(ResourceTest.resourcePrefix, "产品管理", "fa fa-product-hunt fa-fw", 6);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		String parentNodeCode = ResourceTest.resourcePrefix + "_Manage";
		createPTreeNode(tree, null, "fa fa-product-hunt fa-fw", parentNodeCode, "产品管理", "", 1);
		{
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Product", "产品列表", "/prod/product/list", 1);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Project", "产品方案", "/prod/project/list", 2);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_OnSale", "在售列表", "/prod/onsale/list", 3);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Putaway", "产品上架", "/prod/putway/list", 4);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_SoldOut", "产品下架", "/prod/soldout/list", 5);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_PriceAudit", "定价审核", "/prod/priceaudit/list", 6);
			createPTreeNode(tree, parentNodeCode, null, parentNodeCode + "_Package", "产品套餐", "/prod/package/list", 7);
		}
	}
}
