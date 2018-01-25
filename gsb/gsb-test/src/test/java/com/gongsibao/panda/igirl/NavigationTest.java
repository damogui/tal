package com.gongsibao.panda.igirl;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.ncl.NclBatch;
import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "IGIRL";
		this.treePath = "panda/gsb/igirl";
		this.resourceNode = "GSB_IGIRL";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_IGIRL", "IGIRL", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-users fa-fw", "GSB_IGIRL_TM", "商标", "", 1);
		{
			createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_All_" + TradeMarkCase.class.getSimpleName(), "方案生成", "/igirl/trademarkcase/all/list", 1);
			createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_All_" + TradeMark.class.getSimpleName(),     "进度跟进", "/igirl/all/progress/list", 2);
			createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_My_"  + TradeMarkCase.class.getSimpleName(), "我的方案", "/igirl/my/case/list", 3);
			createPTreeNode(tree, "GSB_IGIRL_TM", null, "IGIRL_My_"  +  TradeMark.class.getSimpleName(),    "我的跟进", "/igirl/my/progress/list", 4);

		}
		
		createPTreeNode(tree, null, null, "GSB_IGIRL_BASE", "基础信息", "", 2);
		{
			createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标大类", "/igirl/nclone/all/list", 1);
			createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标小类", "/igirl/ncltwo/all/list", 2);

		}
		createPTreeNode(tree, null, null, "GSB_NCL_TM", "分类", "", 1);
		{
			createPTreeNode(tree, "GSB_NCL_TM", null, "NCL_All_" + NclBatch.class.getSimpleName(), "分类列表", "/ncl/batch/list", 1);
		}
	}
}
