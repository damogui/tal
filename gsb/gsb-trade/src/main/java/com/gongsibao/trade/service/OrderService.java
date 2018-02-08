package com.gongsibao.trade.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    public OrderService(){
        super();
        this.type=SoOrder.class;
    }
    @Override
	public SoOrder byId(Object id) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		SoOrder entity = this.queryFirst(oql);
		return entity;
	}

	private String getSelectFullFields() {

		StringBuilder builder = new StringBuilder();
		builder.append("SoOrder.*,");
		builder.append("SoOrder.products.*,");
		return builder.toString();
	}
}