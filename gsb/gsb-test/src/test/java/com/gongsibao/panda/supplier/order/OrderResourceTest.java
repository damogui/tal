package com.gongsibao.panda.supplier.order;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.*;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.trade.base.*;
import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.u8.base.IU8BankService;

public class OrderResourceTest extends ResourceCreationBase {
    @Test
    public void run() {

        ResourceNode node = resourceNodeService.byCode("Gsb_Supplier_System");
        this.createResourceNodeVouchers(node);
    }

    @Override
    protected void createResourceNodeVouchers(ResourceNode node) {

        ResourceNode node1 = this.createResourceNodeCategory("订单管理", "Gsb_Supplier_Order", node.getId());
        {
            ResourceNode node2 = this.createResourceNodeCategory("我的订单", "Gsb_Supplier_Order_Salesman", node1.getId());
            {
                this.createResourceNodeVoucher(SoOrder.class.getName(), "创建订单", "Gsb_Supplier_Order_Salesman_Add", IOrderService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "全部订单", "Gsb_Supplier_Order_Salesman_All", IOrderService.class.getName(), node2.getId());
                /*我的订单按钮beg*/
                this.createResourceNodeVoucher(SoOrder.class.getName(), "创建订单业绩", "Gsb_Supplier_Order_Salesman_CoPerformance", IOrderService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "创建回款业绩", "Gsb_Supplier_Order_Salesman_CReceivedPerformance", IOrderService.class.getName(), node2.getId());
                /*下拉列表反射beg*/
                this.createResourceNodeVoucher(U8Bank.class.getName(), "u8Bank付款方式", "GSB_Basic_SupplierU8_U8Bank", IU8BankService.class.getName(), node2.getId());


                this.createResourceNodeVoucher(Supplier.class.getName(), "服务商表参照", "GSB_Order_Supplier", ISupplierService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(SupplierDepartment.class.getName(), "服务商部门表参照", "GSB_Order_Supplier_Department", ISupplierDepartmentService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(Salesman.class.getName(), "服务商业务员参照", "GSB_Order_Supplier_Salesman", ISalesmanService.class.getName(), node2.getId());



                /*下拉列表反射end*/

                this.createResourceNodeVoucher(NDepReceivable.class.getName(), "订单业绩", "Gsb_Supplier_Order_Salesman_Performance", INDepReceivableService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "我的回款", "Gsb_Supplier_Order_Salesman_Pay", IPayService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "我的回款业绩", "Gsb_Supplier_Order_Salesman_Received", IPayService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(Refund.class.getName(), "我的退款", "Gsb_Supplier_Order_Salesman_Refund", IRefundService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "我的分期", "Gsb_Supplier_Order_Salesman_Staging", IOrderService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(NOrderCarryover.class.getName(), "我的结转", "Gsb_Supplier_Order_Salesman_Carryover", INOrderCarryoverService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(Contract.class.getName(), "合同管理", "Gsb_Supplier_Order_Salesman_Contract", IContractService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(OrderInvoiceMap.class.getName(), "发票管理", "Gsb_Supplier_Order_Salesman_Invoice", IOrderInvoiceMapService.class.getName(), node2.getId());
//                this.createResourceNodeVoucher(SoOrder.class.getName(), "日统计", "Gsb_Supplier_Order_Salesman_Day_Report", IOrderService.class.getName(), node1.getId());
//                this.createResourceNodeVoucher(SoOrder.class.getName(), "周统计", "Gsb_Supplier_Order_Salesman_Week_Report", IOrderService.class.getName(), node1.getId());
//                this.createResourceNodeVoucher(SoOrder.class.getName(), "月统计", "Gsb_Supplier_Order_Salesman_Month_Report", IOrderService.class.getName(), node1.getId());

                this.createResourceNodeVoucher(OrderProd.class.getName(), "订单明细", "Gsb_Supplier_Order_Salesman_OrderProd", IOrderProdService.class.getName(), node2.getId());
                this.createResourceNodeVoucher(OrderProdItem.class.getName(), "服务明细", "Gsb_Supplier_Order_Salesman_OrderProdItem", IOrderProdService.class.getName(), node2.getId());

            }

            ResourceNode node3 = this.createResourceNodeCategory("部门订单", "Gsb_Supplier_Order_Department", node1.getId());
            {
                this.createResourceNodeVoucher(SoOrder.class.getName(), "全部订单", "Gsb_Supplier_Order_Department_All", IOrderService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(NDepReceivable.class.getName(), "订单业绩", "Gsb_Supplier_Order_Department_Performance", INDepReceivableService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "部门回款", "Gsb_Supplier_Order_Department_Pay", IPayService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "部门回款业绩", "Gsb_Supplier_Order_Department_Received", IPayService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(Refund.class.getName(), "退款订单", "Gsb_Supplier_Order_Department_Refund", IRefundService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "分期订单", "Gsb_Supplier_Order_Department_Staging", IOrderService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(NOrderCarryover.class.getName(), "结转订单", "Gsb_Supplier_Order_Department_Carryover", INOrderCarryoverService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(Contract.class.getName(), "合同管理", "Gsb_Supplier_Order_Department_Contract", IContractService.class.getName(), node3.getId());
                this.createResourceNodeVoucher(OrderInvoiceMap.class.getName(), "发票管理", "Gsb_Supplier_Order_Department_Invoice", IOrderInvoiceMapService.class.getName(), node3.getId());

//                this.createResourceNodeVoucher(SoOrder.class.getName(), "日统计", "Gsb_Supplier_Order_Department_Day_Report", IOrderService.class.getName(), node1.getId());
//                this.createResourceNodeVoucher(SoOrder.class.getName(), "周统计", "Gsb_Supplier_Order_Department_Week_Report", IOrderService.class.getName(), node1.getId());
//                this.createResourceNodeVoucher(SoOrder.class.getName(), "月统计", "Gsb_Supplier_Order_Department_Month_Report", IOrderService.class.getName(), node1.getId());
            }

            ResourceNode node4 = this.createResourceNodeCategory("订单审核", "Gsb_Supplier_Order_Audit", node1.getId());
            {
                this.createResourceNodeVoucher(SoOrder.class.getName(), "订单审核", "Gsb_Supplier_Order_Audit_Order", IOrderService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(NDepReceivable.class.getName(), "订单业绩审核", "Gsb_Supplier_Order_Audit_Performance", INDepReceivableService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "回款审核", "Gsb_Supplier_Pay_Audit", IPayService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(Pay.class.getName(), "回款业绩审核", "Gsb_Supplier_Pay_Audit_Performance", IPayService.class.getName(), node4.getId());


                this.createResourceNodeVoucher(SoOrder.class.getName(), "定价审核", "Gsb_Supplier_Order_Audit_Pricing", IOrderService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(Refund.class.getName(), "退款审核", "Gsb_Supplier_Order_Audit_Refund", IRefundService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "分期审核", "Gsb_Supplier_Order_Audit_Staging", IOrderService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(SoOrder.class.getName(), "结转审核", "Gsb_Supplier_Order_Audit_Carryover", IOrderService.class.getName(), node4.getId());

                this.createResourceNodeVoucher(AuditLog.class.getName(), "合同审核", "Gsb_Supplier_Order_Audit_Contract", IAuditLogService.class.getName(), node4.getId());
                this.createResourceNodeVoucher(AuditLog.class.getName(), "发票审核", "Gsb_Supplier_Order_Audit_Invoice", IAuditLogService.class.getName(), node4.getId());
            }
        }
    }
}