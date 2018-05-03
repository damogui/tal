package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.OrderCooperation;
import com.gongsibao.trade.base.IOrderCooperationService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;

@Service
public class OrderCooperationService extends PersistableService<OrderCooperation> implements IOrderCooperationService {

    public OrderCooperationService() {
        super();
        this.type = OrderCooperation.class;
    }

    @Override
    public List<OrderCooperation> getByOrderId(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("order_id = ?");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }
        List<OrderCooperation> orderCooperations = this.pm.queryList(oql);
        return orderCooperations;
    }
}
