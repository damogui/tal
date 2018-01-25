package com.gongsibao.panda.ncl;
import com.gongsibao.entity.ncl.NclBatch;
import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "NCL";
		this.treePath = "panda/gsb/ncl";
		this.resourceNode = "GSB_NCL";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_NCL", "尼斯分类", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, null, "GSB_NCL_TM", "分类", "", 1);
		{
			createPTreeNode(tree, "GSB_NCL_TM", null, "NCL_All_" + NclBatch.class.getSimpleName(), "分类列表", "/ncl/batch/list", 1);
		}
	}
}
