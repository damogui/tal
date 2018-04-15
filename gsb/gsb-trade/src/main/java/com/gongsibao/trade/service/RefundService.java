package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.trade.base.IRefundService;

@Service
public class RefundService extends PersistableService<Refund> implements IRefundService {

    public RefundService(){
        super();
        this.type=Refund.class;
    }

	@Override
	public List<Refund> queryByOrderId(Integer orderId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Refund.*,refunds.*,prices.*");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public Refund queryById(Integer id) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("Refund.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		return this.queryFirst(oql);
	}
}