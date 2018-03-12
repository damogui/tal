package com.gongsibao.panda.platform.trade;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdItem;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.base.IOrderOperationService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IOrderProdService;
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
		node1 = this.createResourceNodeCategory("订单管理", prefix + "_Manage", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "全部订单", node1.getCode() + "_All_Order", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "我的订单", node1.getCode() + "_My_Order", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "我的订单明细", node1.getCode() + "_My_Order_Detail", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(OrderProd.class.getName(), "订单明细", node1.getCode() + "_Order_Pool", IOrderProdService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(OrderProdItem.class.getName(), "订单服务明细",OrderProdItem.class.getSimpleName(), IOrderProdService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrderDTO.class.getName(), "订单操作", node1.getCode() + "_Order_Operation", IOrderOperationService.class.getName(), node1.getId());			
		}
		
		node1 = this.createResourceNodeCategory("审核中心", prefix + "_Audit", node.getId());
		{
			this.createResourceNodeVoucher(AuditLog.class.getName(), "业绩审核", node1.getCode() + "_Performance", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "回款审核", node1.getCode() + "_Received", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "退款审核", node1.getCode() + "_Refund", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "结转审核", node1.getCode() + "_Carryover", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "发票审核", node1.getCode() + "_Invoice", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "成本审核", node1.getCode() + "_Cost", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "合同审核", node1.getCode() + "_Contract", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "改价审核", node1.getCode() + "_Price_Change", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "分期审核", node1.getCode() + "_Staging", IAuditLogService.class.getName(), node1.getId());
			/*this.createResourceNodeVoucher(AuditLog.class.getName(), "商城充值审核", node1.getCode() + "_Recharge", IAuditLogService.class.getName(), node1.getId());*/

//			回发票审核

		}

		node1 = this.createResourceNodeCategory("成本管理", prefix + "_Cost", node.getId());
		{
//			全部订单
//			全部成本
//			全部回款发票

			this.createResourceNodeVoucher(SoOrder.class.getName(), "成本管理", node1.getCode() + "_Manage", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "收款方管理", node1.getCode() + "_Payee", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "请款审核", node1.getCode() + "_CashOut", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "供应商发票审核", node1.getCode() + "_Invoice", IOrderService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("支付记录", prefix + "_Payment", node.getId());
		{
			this.createResourceNodeVoucher(OrderPayMap.class.getName(), "支付记录", node1.getCode() + "_Payment_Log", IOrderPayMapService.class.getName(), node1.getId());
		}

		/*node1 = this.createResourceNodeCategory("结算中心", prefix + "_Settle", node.getId());
		{
			this.createResourceNodeVoucher(SoOrder.class.getName(), "分成管理", node1.getCode() + "_Commission", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "结算列表", node1.getCode() + "_Settle", IOrderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SoOrder.class.getName(), "打款列表", node1.getCode() + "_Remittance", IOrderService.class.getName(), node1.getId());
		}*/
	}
}
//财务审核
//法务审核
//采购审核
//成本管理