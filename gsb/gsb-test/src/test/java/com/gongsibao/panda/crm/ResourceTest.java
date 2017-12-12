package com.gongsibao.panda.crm;

import org.junit.Before;
import org.netsharp.base.IPersistableService;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.crm.base.ICustomerFollowService;
import com.gongsibao.crm.base.ICustomerOrderService;
import com.gongsibao.crm.base.ICustomerProdMapService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerCompanyMap;
import com.gongsibao.entity.crm.CustomerFollow;
import com.gongsibao.entity.crm.CustomerOrder;
import com.gongsibao.entity.crm.CustomerProdMap;
import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.trade.SoOrder;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "客户管理";
		parentNodeCode = "GSB_CRM";
		pluginName = "客户管理";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("客户管理", "GSB_CRM_Manager", node.getId());
		{
			this.createResourceNodeVoucher(Customer.class.getName(), "全部客户", "CRM_All_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
//			this.createResourceNodeVoucher(Customer.class.getName(), "我的客户", "CRM_My_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "企业信息库", "CRM_Enterprise", ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "客户池", "CRM_Pool_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Customer.class.getName(), "客户操作", "CRM_Operation_" + Customer.class.getSimpleName(), ICustomerService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Dict.class.getName(), "城市", Dict.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerProdMap.class.getName(), "意向产品", CustomerProdMap.class.getSimpleName(), ICustomerProdMapService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "产品", "CRM_"+Product.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerServiceConfig.class.getName(), "客服配置", "CRM_"+CustomerServiceConfig.class.getSimpleName(), ICustomerServiceConfigService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CompanyIntention.class.getName(), "企业信息", CompanyIntention.class.getSimpleName(), ICompanyIntentionService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerCompanyMap.class.getName(), "关联企业", CustomerCompanyMap.class.getSimpleName(), ICustomerCompanyMapService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CustomerFollow.class.getName(), "沟通日志", CustomerFollow.class.getSimpleName(), ICustomerFollowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单记录", SoOrder.class.getSimpleName(), IPersistableService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(CustomerOrder.class.getName(), "订单列表", "CRM_"+CustomerOrder.class.getSimpleName(), ICustomerOrderService.class.getName(), node1.getId());
		}
	}
}






