package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IOrderInvoiceMapService;

import java.sql.Types;
import java.util.List;

@Service
public class OrderInvoiceMapService extends PersistableService<OrderInvoiceMap> implements IOrderInvoiceMapService {

    public OrderInvoiceMapService(){
        super();
        this.type=OrderInvoiceMap.class;
    }

    @Override
    public List<OrderInvoiceMap> getByInvoiceId(Integer invoiceId) {

        Oql oql = new Oql();
        {
            oql.setType(OrderInvoiceMap.class);
            oql.setSelects("orderInvoiceMap.*,soOrder.*,invoice.*");
            oql.setFilter("invoice_id=?");
            oql.getParameters().add("invoiceId", invoiceId, Types.INTEGER);
        }

        List<OrderInvoiceMap> orderInvoiceMaps = this.pm.queryList(oql);

        return orderInvoiceMaps;
    }

    @Override
    public List<OrderInvoiceMap> getByOrderId(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(OrderInvoiceMap.class);
            oql.setSelects("orderInvoiceMap.*,soOrder.*,invoice.*");
            oql.setFilter("order_id=?");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }

        List<OrderInvoiceMap> orderInvoiceMaps = this.pm.queryList(oql);

        return orderInvoiceMaps;
    }
}