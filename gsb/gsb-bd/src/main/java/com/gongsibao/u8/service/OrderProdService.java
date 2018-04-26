package com.gongsibao.u8.service;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.u8.base.IOrderProdService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {
    @Override
    public List<OrderProd> findOrderProdByOrderIds(List<Integer> orderIds) {
        return null;
    }

    @Override
    public Map<Integer, Integer> findPayablePrice(List<Integer> prodIds) {
        return null;
    }

    @Override
    public List<OrderProd> getByOrderId(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("order_id = ?");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }
        List<OrderProd> orderProdList = this.pm.queryList(oql);
        return orderProdList;
    }
}
