package com.gongsibao.panda.product;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.product.ProductPackage;
import com.gongsibao.entity.product.Workflow;
import com.gongsibao.product.base.IProductPackageService;
import com.gongsibao.product.base.IProductService;
import com.gongsibao.product.base.IWorkflowService;

public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_Product";

	@Before
	public void setup() {

		parentNodeName = "产品管理";
		parentNodeCode = ResourceTest.resourcePrefix;
		pluginName = "产品管理";
		seq = 6;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("产品管理", prefix + "_Manage", node.getId());
		{
			this.createResourceNodeVoucher(Product.class.getName(), "产品列表", node1.getCode() + "_Product", IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Workflow.class.getName(), "产品方案", node1.getCode() + "_Project", IWorkflowService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "在售列表", node1.getCode() + "_OnSale", IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "产品上架", node1.getCode() + "_Putaway", IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "产品下架", node1.getCode() + "_SoldOut", IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Product.class.getName(), "定价审核", node1.getCode() + "_PriceAudit", IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ProductPackage.class.getName(), "产品套餐", node1.getCode() + "_Package", IProductPackageService.class.getName(), node1.getId());
		}

	}
}