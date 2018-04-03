package com.gongsibao.igirl.service;

import com.gongsibao.entity.igirl.OrderProdCase;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.base.IOrderProdCaseService;
import com.gongsibao.trade.base.IOrderProdService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProdCaseService extends PersistableService<OrderProdCase> implements IOrderProdCaseService {

    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

    public OrderProdCaseService() {
        super();
        this.type = OrderProdCase.class;
    }

    @Override
    public List<OrderProdCase> byCaseId(Integer caseId) {
        Oql oql = new Oql();
        {
            oql.setType(OrderProdCase.class);
            oql.setSelects("OrderProdCase.*");
            oql.setFilter("caseId=?");
            oql.getParameters().add("@caseId", caseId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public List<OrderProdCase> byOrderId(Integer orderId) { Oql oql = new Oql();
        {
            oql.setType(OrderProdCase.class);
            oql.setSelects("OrderProdCase.*");
            oql.setFilter("orderId=?");
            oql.getParameters().add("@orderId", orderId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public boolean updateToSettle(Integer caseId) {
        if (null == caseId || caseId == 0) {
            return false;
        }

        List<OrderProdCase> orderProdCases = byCaseId(caseId);
        if (null == orderProdCases || orderProdCases.isEmpty()) {
            return false;
        }

        List<Integer> orderProdIds = new ArrayList<>();

        for (OrderProdCase orderProdCase : orderProdCases) {
            orderProdIds.add(orderProdCase.getOrderProdId());
        }

        return orderProdService.updateSettleStatus(orderProdIds, SettleStatus.NO_SETTLEMENT);
    }
}
