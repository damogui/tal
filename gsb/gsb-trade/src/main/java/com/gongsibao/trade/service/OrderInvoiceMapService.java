package com.gongsibao.trade.service;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderInvoiceMap;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderInvoiceMapService extends PersistableService<OrderInvoiceMap> implements IOrderInvoiceMapService {

    public OrderInvoiceMapService() {
        super();
        this.type = OrderInvoiceMap.class;
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

    @Override
    public List<OrderInvoiceMap> getByInvoiceIdList(List<Integer> invoiceIdList) {

        if (CollectionUtils.isEmpty(invoiceIdList)) {
            return new ArrayList<>();
        }
        String invoiceIds = StringManager.join(",", invoiceIdList);

        Oql oql = new Oql();
        {
            oql.setType(OrderInvoiceMap.class);
            oql.setSelects("orderInvoiceMap.*,soOrder.*,invoice.*");
            oql.setFilter("invoice_id in (" + invoiceIds + ")");
        }

        return this.pm.queryList(oql);
    }

    @Override
    public boolean delByInvoiceId(Integer invoiceId) {
        String sql = "DELETE FROM so_order_invoice_map WHERE invoice_id = " + invoiceId;
        return this.pm.executeNonQuery(sql, null) > 0;
    }
}