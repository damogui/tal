package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderInvoiceMap;

import java.util.List;

public interface IOrderInvoiceMapService extends IPersistableService<OrderInvoiceMap> {

    List<OrderInvoiceMap> getByInvoiceId(Integer invoiceId);

    List<OrderInvoiceMap> getByOrderId(Integer orderId);

    List<OrderInvoiceMap> getByInvoiceIdList(List<Integer> invoiceIdList);

    boolean delByInvoiceId(Integer invoiceId);
}