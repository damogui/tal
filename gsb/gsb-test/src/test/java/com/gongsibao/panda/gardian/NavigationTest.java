package com.gongsibao.panda.gardian;
import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.gardian.baseinfo.Provides;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

public class NavigationTest extends NavigationBase {

	@Before
	public void setup() {
		this.treeName = "GARDIAN";
		this.treePath = "panda/gsb/gardian";
		this.resourceNode = "GSB_GARDIAN";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_GARDIAN", "GARDIAN", "fa fa-users fa-fw", 3);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {


		
		createPTreeNode(tree, null, null, "GSB_GARDIAN_BASE", "基础信息", "", 2);
		{
			createPTreeNode(tree, "GSB_GARDIAN_BASE", null, "GARDIAN_BASE_" + Device.class.getSimpleName(), "设备列表", "/gardian/device/list", 1);
			createPTreeNode(tree, "GSB_GARDIAN_BASE", null, "GARDIAN_BASE_" + Provides.class.getSimpleName(), "服务列表", "/gardian/provides/list", 1);
			//createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标小类", "/igirl/ncltwo/all/list", 2);

		}
	}
}
