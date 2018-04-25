package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.trade.base.IOrderPayMapService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.util.List;

@Service
public class OrderPayMapService extends PersistableService<OrderPayMap> implements IOrderPayMapService {


    public OrderPayMapService() {
        super ();
        this.type = OrderPayMap.class;
    }
    @Override
    public List<OrderPayMap> queryByOrderIdPayId(Integer orderId, Integer payId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("orderId=" + orderId + "");
            oql.setFilter ("payId=" + payId + "");
        }
        return this.pm.queryList (oql);
    }
}
