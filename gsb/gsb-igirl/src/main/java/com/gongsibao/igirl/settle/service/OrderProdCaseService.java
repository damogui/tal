package com.gongsibao.igirl.settle.service;

import com.gongsibao.entity.igirl.settle.OrderProdCase;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.igirl.settle.base.IOrderProdCaseService;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.trade.base.IOrderProdService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.DeleteBuilder;

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
    public List<OrderProdCase> byIds(List<Integer> ids) {
        if (null == ids || ids.isEmpty()) {
            return null;
        }

        Oql oql = new Oql();
        {
            oql.setType(OrderProdCase.class);
            oql.setSelects("OrderProdCase.*, OrderProdCase.orderProd.* ");
            oql.setFilter("id IN (" + StringManager.join(",", ids) + ") ");
        }
        return this.queryList(oql);
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
