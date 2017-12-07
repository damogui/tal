package com.gongsibao.panda.trade;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_Trade";

	@Before
	public void setup() {

		parentNodeName = "交易中心";
		parentNodeCode = ResourceTest.resourcePrefix;
		pluginName = "交易中心";
		seq = 8;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("订单管理", prefix + "_Manager", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部订单", node1.getCode() + "_All_Order", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "我的订单", node1.getCode() + "_My_Order", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "我的订单明细", node1.getCode() + "_My_Order_Detail", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "订单池", node1.getCode() + "_Order_Pool", IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("订单审核", prefix + "_Audit", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "改价审核", node1.getCode() + "_Price_Change", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "合同审核", node1.getCode() + "_Contract_Audit", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "分期审核", node1.getCode() + "_Installment", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "发票审核", node1.getCode() + "_Invoice_Audit", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "收款审核", node1.getCode() + "_Receipt_Audit", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "商城充值审核", node1.getCode() + "_Recharge", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "退单审核", node1.getCode() + "_Refund_Aduit", IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("成本中心", prefix + "_Cost", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部客户", node1.getCode() + "_", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部客户", node1.getCode() + "_", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部客户", node1.getCode() + "_", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部客户", node1.getCode() + "_", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部客户", node1.getCode() + "_", IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("支付记录", prefix + "_Payment", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "支付记录", node1.getCode() + "_Payment_Log", IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("结算中心", prefix + "_Settle", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "分成管理", node1.getCode() + "_Commission", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "打款管理", node1.getCode() + "_Remittance", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "结算管理", node1.getCode() + "_Settle", IOrderService.class.getName(), node1.getId());
		}
	}
}
