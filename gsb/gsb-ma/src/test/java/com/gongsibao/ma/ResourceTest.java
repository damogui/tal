package com.gongsibao.ma;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandMatchingDetail;
import com.gongsibao.entity.ma.AcquisitionDemandPccDetail;
import com.gongsibao.entity.ma.DemandFixedAssets;
import com.gongsibao.entity.ma.DemandIntangibleAssets;
import com.gongsibao.entity.ma.DemandQualificationDetail;
import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.SellingDemandBranchCompany;
import com.gongsibao.entity.ma.SellingDemandSubsidiaryCompany;
import com.gongsibao.entity.ma.SellingDemandTurnoverDetail;
import com.gongsibao.ma.base.IAcquisitionDemandMatchingDetailService;
import com.gongsibao.ma.base.IAcquisitionDemandPccDetailService;
import com.gongsibao.ma.base.IAcquisitionDemandService;
import com.gongsibao.ma.base.IDemandFixedAssetsService;
import com.gongsibao.ma.base.IDemandIntangibleAssetsService;
import com.gongsibao.ma.base.IMaOrderService;
import com.gongsibao.ma.base.ISellingDemandBranchCompanyService;
import com.gongsibao.ma.base.ISellingDemandQualificationDetailService;
import com.gongsibao.ma.base.ISellingDemandService;
import com.gongsibao.ma.base.ISellingDemandSubsidiaryCompanyService;
import com.gongsibao.ma.base.ISellingDemandTurnoverDetailService;

public class ResourceTest extends ResourceCreationBase {

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	@Before
	public void setup() {

		parentNodeName = "股权转让";
		parentNodeCode = "GSB_MA";
		pluginName = "股权转让";
		seq = 1;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("出售需求", "MA_Selling", node.getId());{
		
			this.createResourceNodeVoucher(SellingDemand.class.getName(), "需求列表", SellingDemand.class.getSimpleName(), ISellingDemandService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SellingDemand.class.getName(), "我的出售需求", "My"+SellingDemand.class.getSimpleName(), ISellingDemandService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SellingDemand.class.getName(), "待审核", "UNAUDIT"+SellingDemand.class.getSimpleName(), ISellingDemandService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(SellingDemandSubsidiaryCompany.class.getName(), "子公司", SellingDemandSubsidiaryCompany.class.getSimpleName(), ISellingDemandSubsidiaryCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SellingDemandBranchCompany.class.getName(), "分公司", SellingDemandBranchCompany.class.getSimpleName(), ISellingDemandBranchCompanyService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandIntangibleAssets.class.getName(), "无形资产", "Selling"+DemandIntangibleAssets.class.getSimpleName(), IDemandIntangibleAssetsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandFixedAssets.class.getName(), "固定资产", "Selling"+DemandFixedAssets.class.getSimpleName(), IDemandFixedAssetsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SellingDemandTurnoverDetail.class.getName(), "流水明细", SellingDemandTurnoverDetail.class.getSimpleName(), ISellingDemandTurnoverDetailService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandQualificationDetail.class.getName(), "资质明细", "Selling"+DemandQualificationDetail.class.getSimpleName(), ISellingDemandQualificationDetailService.class.getName(), node1.getId());
		
			
			
		}
		
		node1 = this.createResourceNodeCategory("收购需求", "MA_Acquisition", node.getId());
		{
			this.createResourceNodeVoucher(AcquisitionDemand.class.getName(), "需求列表", AcquisitionDemand.class.getSimpleName(), IAcquisitionDemandService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AcquisitionDemand.class.getName(), "我的出售需求", "My"+AcquisitionDemand.class.getSimpleName(), IAcquisitionDemandService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AcquisitionDemandPccDetail.class.getName(), "意向区域", AcquisitionDemandPccDetail.class.getSimpleName(), IAcquisitionDemandPccDetailService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandIntangibleAssets.class.getName(), "无形资产", "Acquisition"+DemandIntangibleAssets.class.getSimpleName(), IDemandIntangibleAssetsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandFixedAssets.class.getName(), "固定资产", "Acquisition"+DemandFixedAssets.class.getSimpleName(), IDemandFixedAssetsService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DemandQualificationDetail.class.getName(), "资质明细", "Acquisition"+DemandQualificationDetail.class.getSimpleName(), ISellingDemandQualificationDetailService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AcquisitionDemandMatchingDetail.class.getName(), "匹配明细", AcquisitionDemandMatchingDetail.class.getSimpleName(), IAcquisitionDemandMatchingDetailService.class.getName(), node1.getId());
			
		}
		
		node1 = this.createResourceNodeCategory("订单管理", "MA_Order", node.getId());{

			this.createResourceNodeVoucher(MaOrder.class.getName(), "订单列表", MaOrder.class.getSimpleName(), IMaOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(MaOrder.class.getName(), "我的订单", "My"+MaOrder.class.getSimpleName(), IMaOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(MaOrder.class.getName(), "组长待审核", "Leader_UNAUDIT_"+MaOrder.class.getSimpleName(), IMaOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(MaOrder.class.getName(), "VP待审核", "VP_UNAUDIT_"+MaOrder.class.getSimpleName(), IMaOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(MaOrder.class.getName(), "审核通过", "VP_VERIFIED_"+MaOrder.class.getSimpleName(), IMaOrderService.class.getName(), node1.getId());
		}

		
		node1 = this.createResourceNodeCategory("渠道商", "MA_Channel", node.getId());{
			
			this.createResourceNodeVoucher(SellingDemand.class.getName(), "出售需求", "Channel"+SellingDemand.class.getSimpleName(), ISellingDemandService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AcquisitionDemand.class.getName(), "收购需求", "Channel"+AcquisitionDemand.class.getSimpleName(), IAcquisitionDemandService.class.getName(), node1.getId());
		}
	}
}
