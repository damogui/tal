package com.gongsibao.product.service;

import com.gongsibao.entity.product.IncomeSettle;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.product.base.IIncomeSettleService;
import com.gongsibao.product.base.IOrderProdService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {

    public OrderProdService() {
        super();
        this.type = OrderProd.class;
    }

    @Override
    public OrderProd getById(Integer Id) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", Id, Types.INTEGER);
        }
        return this.pm.queryFirst(oql);
    }
}