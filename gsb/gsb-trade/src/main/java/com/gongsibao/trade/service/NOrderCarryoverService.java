package com.gongsibao.trade.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.trade.base.INOrderCarryoverService;

@Service
public class NOrderCarryoverService extends PersistableService<NOrderCarryover> implements INOrderCarryoverService{

    public NOrderCarryoverService(){
        super();
        this.type=NOrderCarryover.class;
    }

	@Override
	public NOrderCarryover queryByFormOrderId(Integer orderId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("form_order_id=?");
			oql.setOrderby("create_time desc");
			oql.getParameters().add("form_order_id", orderId, Types.INTEGER);
		}
		return this.queryFirst(oql);
	}
}
