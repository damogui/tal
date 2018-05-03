package com.gongsibao.trade.service.settle;

import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.entity.trade.settle.OrderProdSettle;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.settle.IOrderProdSettleService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProdSettleService extends PersistableService<OrderProdSettle> implements IOrderProdSettleService {

    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

    public OrderProdSettleService() {
        super();
        this.type = OrderProdSettle.class;
    }

    @Override
    public List<OrderProdSettle> byIds(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            return null;
        }

        Oql oql = new Oql();
        {
            oql.setType(OrderProdSettle.class);
            oql.setSelects("OrderProdSettle.*, OrderProdSettle.orderProd.*, OrderProdSettle.soOrder.* ");
            oql.setFilter("id IN (" + StringManager.join(",", ids) + ") ");
        }
        return this.queryList(oql);
    }

    @Override
    public List<OrderProdSettle> byCaseId(Integer caseId) {
        Oql oql = new Oql();
        {
            oql.setType(OrderProdSettle.class);
            oql.setSelects("OrderProdSettle.*");
            oql.setFilter("caseId=?");
            oql.getParameters().add("@caseId", caseId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public List<OrderProdSettle> byOrderId(Integer orderId) { Oql oql = new Oql();
        {
            oql.setType(OrderProdSettle.class);
            oql.setSelects("OrderProdSettle.*");
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

        List<OrderProdSettle> orderProdCases = byCaseId(caseId);
        if (null == orderProdCases || orderProdCases.isEmpty()) {
            return false;
        }

        List<Integer> orderProdIds = new ArrayList<>();

        for (OrderProdSettle orderProdCase : orderProdCases) {
            orderProdIds.add(orderProdCase.getOrderProdId());
        }

        return orderProdService.updateSettleStatus(orderProdIds, SettleStatus.NO_SETTLEMENT);
    }

    @Override
    public boolean deleteByCaseId(Integer caseId) {
        DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
        {
            deleteBuilder.deleteFrom(MtableManager.getMtable(this.type).getTableName());
            deleteBuilder.where(" case_id = " + caseId);
        }
        return this.pm.executeNonQuery(deleteBuilder.toSQL(), null) > 0;
    }


}
