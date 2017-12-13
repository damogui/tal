package com.gongsibao.panda.ma;

import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.SellingDemand;

public class NavigationTest extends NavigationBase{


	@Before
	public void setup() {
		this.treeName = "股权转让";
		this.treePath = "panda/gsb/ma";
		this.resourceNode = "GSB_MA";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_MA", "股权转让", "fa fa-cny fa-fw", 1);
	}

	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-minus-square-o fa-fw", "MA_Selling", "出售需求", "",1);
		{
			createPTreeNode(tree, "MA_Selling", null, SellingDemand.class.getSimpleName(), "需求列表", "/ma/selling/list",1);
			createPTreeNode(tree, "MA_Selling", null, "My"+SellingDemand.class.getSimpleName(), "我的出售需求","/ma/selling/my/list",2);
			createPTreeNode(tree, "MA_Selling", null, "UNAUDIT"+SellingDemand.class.getSimpleName(), "待审核", "/ma/selling/unaudit/list",3);
		}

		createPTreeNode(tree, null, "fa fa-plus-square-o fa-fw", "MA_Acquisition", "收购需求", "",2);
		{
			createPTreeNode(tree, "MA_Acquisition", null, AcquisitionDemand.class.getSimpleName(),"需求列表", "/ma/acquisition/list",1);
			createPTreeNode(tree, "MA_Acquisition", null, "My"+AcquisitionDemand.class.getSimpleName(), "我的收购需求","/ma/acquisition/my/list",2);
		}
		
		createPTreeNode(tree, null, "fa fa-files-o fa-fw", "MA_Order", "订单管理", "",3);
		{
			createPTreeNode(tree, "MA_Order", null, MaOrder.class.getSimpleName(), "订单列表", "/ma/order/list",1);
			createPTreeNode(tree, "MA_Order", null, "My"+MaOrder.class.getSimpleName(), "我的订单","/ma/order/my/list",2);
			createPTreeNode(tree, "MA_Order", null, "Leader_UNAUDIT_"+MaOrder.class.getSimpleName(), "组长待审核", "/ma/order/leaderunaudit/list",3);
			createPTreeNode(tree, "MA_Order", null, "VP_UNAUDIT_"+MaOrder.class.getSimpleName(), "VP待审核", "/ma/order/vpunaudit/list",4);
			createPTreeNode(tree, "MA_Order", null, "VP_VERIFIED_"+MaOrder.class.getSimpleName(), "审核通过","/ma/order/vpverified/list",5);
		}
		
		createPTreeNode(tree, null, "fa fa-plus-square-o fa-fw", "MA_Channel", "渠道商", "",4);
		{
			createPTreeNode(tree, "MA_Channel", null, "Channel"+SellingDemand.class.getSimpleName(),"出售需求", "/ma/selling/channel/list",1);
			createPTreeNode(tree, "MA_Channel", null, "Channel"+AcquisitionDemand.class.getSimpleName(), "收购需求","/ma/acquisition/channel/list",2);
		}
		
	}
}
