package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.trade.base.INDepRefundService;

import java.sql.Types;
import java.util.List;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepRefundService extends PersistableService<NDepRefund> implements INDepRefundService {

    public NDepRefundService() {
        super();
        this.type = NDepRefund.class;
    }

    @Override
    public List<NDepRefund> queryByRefundId(Integer refundId) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setFilter("refund_id=?");
        oql.setSelects("*");
        oql.getParameters().add("@refund_id", refundId, Types.INTEGER);
        return super.queryList(oql);

    }
}
