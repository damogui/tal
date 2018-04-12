package com.gongsibao.panda.supplier.igirl;
import com.gongsibao.entity.igirl.ic.baseinfo.*;
import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.HelpBook;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.TransferTradeMark;
import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.tm.baseinfo.NclBatch;
import com.gongsibao.entity.igirl.tm.baseinfo.NclMap;
import com.gongsibao.entity.igirl.tm.baseinfo.SupplierNewInfo;
import com.gongsibao.entity.igirl.tm.baseinfo.SupplierSiteInfo;

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
				createPTreeNode(tree, "GSB_IGIRL_BASE", null, "NCL_All_"+NclMap.class.getSimpleName(), "尼斯映射", "/igirl/nclmap/list", 5);
			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_SITE", "微门户", "",5);
			{
				createPTreeNode(tree, "GSB_IGIRL_SITE", null, "IGRIL_SITE_" + SupplierSiteInfo.class.getSimpleName(), "站点信息", "/igirl/siteinfo/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_SITE", null, "IGRIL_SITE_" + SupplierNewInfo.class.getSimpleName(), "最新资讯", "/igirl/newinfo/list", 2);
			}
			createPTreeNode(tree, "GSB_TRADE_AI", null, "GSB_IGIRL_ABOUT", "关于", "",6);
			{
				createPTreeNode(tree, "GSB_IGIRL_ABOUT", null, "IGRIL_ABOUT_" + HelpBook.class.getSimpleName(), "使用手册", "/igirl/help/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_ABOUT", null, "IGRIL_ABOUT_ADMINISTRATOR", "管理员篇", "/igirl/help_administrator/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_ABOUT", null, "IGRIL_ABOUT_SALESMAN", "业务员篇", "/igirl/help_salesman/list", 3);
				createPTreeNode(tree, "GSB_IGIRL_ABOUT", null, "IGRIL_ABOUT_MANAGER", "部门负责人篇", "/igirl/help_manager/list", 4);
				createPTreeNode(tree, "GSB_IGIRL_ABOUT", null, "IGRIL_ABOUT_CUSTOMER", "客户篇", "/igirl/help_customer/list", 5);
			}
			
		}
		createPTreeNode(tree, null, null, "GSB_IC_AI", "智能工商", "", 2);
		{
			createPTreeNode(tree, "GSB_IC_AI", null, "GSB_IGIRL_IC_BASE", "基础信息", "",6);
			{
				createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + AreaOne.class.getSimpleName(), "省级列表", "/igirl/ic/areaone/all/list", 1);
				createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + AreaTwo.class.getSimpleName(), "地市列表", "/igirl/ic/areatwo/all/list", 2);
				createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + ChapterOne.class.getSimpleName(), "刻章区", "/igirl/ic/chapterone/all/list", 3);
				createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + ChapterTwo.class.getSimpleName(), "刻章公司", "/igirl/ic/chaptertwo/all/list", 4);
                createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + EntLicType.class.getSimpleName(), "证照类型", "/igirl/ic/entlictype/all/list", 5);
                createPTreeNode(tree, "GSB_IGIRL_IC_BASE", null, "IGRIL_IC_BASE_" + Nationality.class.getSimpleName(), "国籍", "/igirl/ic/nationality/all/list", 6);

			}

		}
		
	}
}
