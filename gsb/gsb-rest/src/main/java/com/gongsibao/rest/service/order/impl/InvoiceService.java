package com.gongsibao.rest.service.order.impl;

import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.rest.service.order.IInvoiceService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import com.gongsibao.trade.service.OrderInvoiceMapService;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: 发票服务
 * @date $ $
 */
@Service
public class InvoiceService implements IInvoiceService {
    com.gongsibao.trade.base.IInvoiceService baseInvoiceService = ServiceFactory.create(com.gongsibao.trade.base.IInvoiceService.class);

    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);

    @Override
    public Invoice byId(int invoiceId) {
        return baseInvoiceService.byId(invoiceId);
    }

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return baseInvoiceService.save(invoice);
    }

    @Override
    public void saveOrderInvoiceMaps(List<OrderInvoiceMap> orderInvoiceMapList) {
        orderInvoiceMapService.saves(orderInvoiceMapList);
    }
}
