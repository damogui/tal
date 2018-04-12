package com.gongsibao.igirl.settle.service;

import com.gongsibao.entity.igirl.settle.SettleOrder;
import com.gongsibao.igirl.settle.base.ISettleOrderService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;

@Service
public class SettleOrderService extends PersistableService<SettleOrder> implements ISettleOrderService {

    public SettleOrderService() {
        super();
        this.type = SettleOrder.class;
    }


    @Override
    public List<SettleOrder> bySettleId(Integer settleId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("SettleOrder.*,SettleOrder.orderProd.{productName,price},SettleOrder.soOrder.{no}");
            oql.setFilter("settleId=?");
            oql.getParameters().add("settleId", settleId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

}
