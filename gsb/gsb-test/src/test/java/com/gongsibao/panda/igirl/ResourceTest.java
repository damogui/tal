package com.gongsibao.panda.igirl;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.igirl.base.INCLOneService;
import com.gongsibao.igirl.base.INCLTwoService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "IGIRL";
		parentNodeCode = "GSB_IGIRL";
		pluginName = "IGIRL";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("商标", "GSB_IGIRL_TM", node.getId());
		{
			this.createResourceNodeVoucher(TradeMarkCase.class.getName(), "方案生成", "IGIRL_All_" + TradeMarkCase.class.getSimpleName(), ITradeMarkCaseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMarkCase.class.getName(), "我的方案", "IGIRL_My_" + TradeMarkCase.class.getSimpleName(), ITradeMarkCaseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "进度跟进", "IGIRL_All_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "我的跟进", "IGIRL_My_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("基础信息", "GSB_IGIRL_BASE", node.getId());
		{
			this.createResourceNodeVoucher(NCLOne.class.getName(), "商标大类", "IGRIL_BASE_" + NCLOne.class.getSimpleName(), INCLOneService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCLTwo.class.getName(), "商标小类", "IGRIL_BASE_" +NCLTwo.class.getSimpleName(), INCLTwoService.class.getName(), node1.getId());
		}
	}
}





