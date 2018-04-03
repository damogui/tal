package com.gongsibao.panda.platform.trade;

import com.gongsibao.entity.trade.*;
import com.gongsibao.trade.base.*;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.u8.base.ISoOrderService;

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
		
            this.createResourceNodeVoucher(SoOrder.class.getName(), "全部订单", "Operation_Order_All", ISoOrderService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SoOrder.class.getName(), "订单池", "Operation_Order_Pool", ISoOrderService.class.getName(), node1.getId());

            this.createResourceNodeVoucher(NDepReceivable.class.getName(), "运营平台订单业绩", "Operation_Order_Performance", INDepReceivableService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(NDepPay.class.getName(), "运营平台回款业绩", "Operation_Order_Received", INDepPayService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(Refund.class.getName(), "退款订单", "Operation_Order_Refund", IRefundService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SoOrder.class.getName(), "分期订单", "Operation_Order_Staging", IOrderService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(NOrderCarryover.class.getName(), "结转订单", "Operation_Order_Carryover", INOrderCarryoverService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(Contract.class.getName(), "合同管理", "Operation_Order_Contract", IContractService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(Invoice.class.getName(), "申请发票", "Operation_Order_Invoice", IInvoiceService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SoOrder.class.getName(), "日统计", "Operation_Order_Day_Report", IOrderService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SoOrder.class.getName(), "周统计", "Operation_Order_Week_Report", IOrderService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(SoOrder.class.getName(), "月统计", "Operation_Order_Month_Report", IOrderService.class.getName(), node1.getId());
            
            this.createResourceNodeVoucher(File.class.getName(), "合同附件", "Operation_Order_Contract_File", IFileService.class.getName(), node1.getId());
            //this.createResourceNodeVoucher(File.class.getName(), "发票附件", "Operation_Order_Invoice_File", IFileService.class.getName(), node1.getId());
            //this.createResourceNodeVoucher(AuditLog.class.getName(), "审核记录", "Operation_Order_AuditLog", IAuditLogService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("审核中心", prefix + "_Audit", node.getId());
		{
			this.createResourceNodeVoucher(AuditLog.class.getName(), "业绩审核", node1.getCode() + "_Performance", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "回款审核", "GSB_Trade_Audit_Received", IAuditLogService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(AuditLog.class.getName(), "回款业绩审核", node1.getCode() + "_ReceivedPer",
                    IAuditLogService.class.getName(), node1.getId());

            this.createResourceNodeVoucher(AuditLog.class.getName(), "退款审核", node1.getCode() + "_Refund", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "结转审核", node1.getCode() + "_Carryover", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "发票审核", node1.getCode() + "_Invoice", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "成本审核", node1.getCode() + "_Cost", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "合同审核", node1.getCode() + "_Contract", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "改价审核", node1.getCode() + "_Price_Change", IAuditLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditLog.class.getName(), "分期审核", node1.getCode() + "_Staging", IAuditLogService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(Contract.class.getName(), "合同审核界面", node1.getCode() + "_Form_Contract", IContractService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Invoice.class.getName(), "发票审核界面", node1.getCode() + "_Form_Invoice", IInvoiceService.class.getName(), node1.getId());

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