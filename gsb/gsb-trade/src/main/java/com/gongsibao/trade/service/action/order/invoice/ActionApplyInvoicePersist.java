package com.gongsibao.trade.service.action.order.invoice;

import java.util.Map;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

public class ActionApplyInvoicePersist implements IAction {

    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method
        Invoice invoice = (Invoice) ctx.getItem();
        Map<String, Object> paraMap = ctx.getStatus();
        IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);
        invoice.toNew();
        invoice.setFileId(0);
        invoice.setRemark("");
        invoice.setAuditStatusId(AuditStatusType.Dsh);//初始状态
        Integer orderId = Integer.parseInt(paraMap.get("orderId").toString());
        SoOrder order = soOrderService.getByOrderId(orderId);
        invoice.setSupplierId(order.getSupplierId());
        invoice.setDepartmentId(order.getDepartmentId());
        invoice.setSalesmanId(order.getOwnerId());
        Invoice temp = invoiceService.save(invoice);
        ctx.setItem(temp);
        if (temp != null && temp.getId() != null) {
            IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);
            OrderInvoiceMap orderInvoice = new OrderInvoiceMap();
            orderInvoice.toNew();
            orderInvoice.setInvoiceId(temp.getId());
            orderInvoice.setOrderId(orderId);
            orderInvoiceMapService.save(orderInvoice);
        }

    }

}
