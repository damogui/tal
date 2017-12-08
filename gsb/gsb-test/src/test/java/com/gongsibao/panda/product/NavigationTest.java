package com.gongsibao.panda.product;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;


public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "产品管理";
		this.treePath = "panda/gsb/product";
		this.resourceNode = "GSB_Product";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_Product", "产品管理", "fa fa-users fa-fw", 6);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		String prefix = ResourceTest.resourcePrefix;
		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_Product_Manager", "产品管理", "", 1);
		{
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_Product" , "产品列表", "/prod/product/list", 1);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_OnSale" , "在售列表", "/prod/onsale/list", 2);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_PriceAudit", "定价审核", "/prod/priceaudit/list", 3);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_Putaway" , "产品上架", "/prod/putway/list", 4);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_SoldOut" , "产品下架", "/prod/soldout/list", 5);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_Project" , "产品方案", "/prod/project/list", 6);
			createPTreeNode(tree, "GSB_Product_Manager", null, prefix + "_Package", "产品套餐", "/prod/package/list", 7);
		}
	}
}
