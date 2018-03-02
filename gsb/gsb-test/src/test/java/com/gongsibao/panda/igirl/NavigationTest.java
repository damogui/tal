package com.gongsibao.panda.igirl;
import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.TransferTradeMark;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;
import com.gongsibao.entity.igirl.baseinfo.SupplierNewInfo;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;
import org.netsharp.panda.plugin.entity.PNavigationItem;

public class NavigationTest extends NavigationBase {

//	@Before
//	public void setup() {
//		this.treeName = "IGIRL";
//		this.treePath = "panda/gsb/igirl";
//		this.resourceNode = "GSB_IGIRL";
//	}

	public void createAccodions() {

		//this.doCreateAccodions("GSB_IGIRL", "IGIRL", "fa fa-users fa-fw", 3);
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

		createPTreeNode(tree, null, null, "GSB_TRADE_AI", "智能商标", "", 1);{
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_TM", "商标申请", "", 1);
			{
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_All_" + TradeMarkCase.class.getSimpleName(), "方案生成", "/igirl/trademarkcase/all/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_All_" + TradeMark.class.getSimpleName(),     "进度跟进", "/igirl/all/progress/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_My_"  + TradeMarkCase.class.getSimpleName(), "我的方案", "/igirl/my/case/list", 3);
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_My_"  +  TradeMark.class.getSimpleName(),    "我的跟进", "/igirl/my/progress/list", 4);
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_Dp_"  + TradeMarkCase.class.getSimpleName(), "部门方案", "/igirl/dp/case/list", 5);
				createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_Dp_"  + TradeMark.class.getSimpleName(),    "部门跟进", "/igirl/dp/progress/list", 6);

			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_CTM", "商标变更", "", 2);
			{
				createPTreeNode(tree, "GSB_IGIRL_CTM", null, "IGIRL_All_" + ChangeTradeMark.class.getSimpleName(), "方案生成", "/igirl/changetrademark/all/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_CTM", null, "IGIRL_My_"  + ChangeTradeMark.class.getSimpleName(),    "我的跟进", "/igirl/myctm/progress/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_CTM", null, "IGIRL_Dp_"  + ChangeTradeMark.class.getSimpleName(),    "部门跟进", "/igirl/dpctm/progress/list", 3);

			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_TTM", "商标转让", "", 3);
			{
				createPTreeNode(tree, "GSB_IGIRL_TTM", null, "IGIRL_All_" + TransferTradeMark.class.getSimpleName(), "方案生成", "/igirl/transfertrademark/all/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_TTM", null, "IGIRL_My_"  + TransferTradeMark.class.getSimpleName(),    "我的跟进", "/igirl/myttm/progress/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_TTM", null, "IGIRL_Dp_"  + TransferTradeMark.class.getSimpleName(),    "部门跟进", "/igirl/dpttm/progress/list", 3);

			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_BASE", "基础信息", "",4);
			{
				createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标大类", "/igirl/nclone/all/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标小类", "/igirl/ncltwo/all/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_BASE", null, "NCL_All_" + NclBatch.class.getSimpleName(), "尼斯期间", "/ncl/batch/list", 3);
				createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + IGirlConfig.class.getSimpleName(), "参数设置", "/igirl/config/list", 4);
			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_SITE", "微门户", "",5);
			{
				createPTreeNode(tree, "GSB_IGIRL_SITE", null, "IGRIL_SITE_" + SupplierSiteInfo.class.getSimpleName(), "站点信息", "/igirl/siteinfo/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_SITE", null, "IGRIL_SITE_" + SupplierNewInfo.class.getSimpleName(), "最新资讯", "/igirl/newinfo/list", 2);
			}
			
		}
		
		
	}
}
