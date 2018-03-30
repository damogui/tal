package com.gongsibao.igirl.service;

import com.gongsibao.entity.igirl.OrderProdCase;
import com.gongsibao.igirl.base.IOrderProdCaseService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;

@Service
public class OrderProdCaseService extends PersistableService<OrderProdCase> implements IOrderProdCaseService {

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
}
