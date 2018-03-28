package com.gongsibao.trade.service.action.order.invoice;

import java.util.Map;

import com.gongsibao.bd.base.IFileService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IInvoiceService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

public class ActionApplyInvoicePersist implements IAction {

    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    IFileService fileService = ServiceFactory.create(IFileService.class);

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

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
        invoice = invoiceService.save(invoice);
        saveOrderInvoiceMap(invoice, orderId);
        saveFiles(invoice);
        //更新发票
        ctx.setItem(invoice);
    }

    //region 私有方法
    //保存支票和订单的中间表
    private void saveOrderInvoiceMap(Invoice invoice, Integer orderId) {
        if (invoice != null && invoice.getId() != null) {
            OrderInvoiceMap orderInvoice = new OrderInvoiceMap();
            orderInvoice.toNew();
            orderInvoice.setInvoiceId(invoice.getId());
            orderInvoice.setOrderId(orderId);
            orderInvoiceMapService.save(orderInvoice);
        }
    }

    //附件的保存
    private void saveFiles(Invoice invoice) {
        for (File f : invoice.getFiles()) {
            f.setFormId(invoice.getId());
        }
        if (CollectionUtils.isNotEmpty(invoice.getFiles())) {
            fileService.saves(invoice.getFiles());
        }
    }
    // endregion

}
