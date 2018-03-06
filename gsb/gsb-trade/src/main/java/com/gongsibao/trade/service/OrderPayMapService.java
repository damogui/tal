package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.trade.base.IOrderPayMapService;

@Service
public class OrderPayMapService extends PersistableService<OrderPayMap> implements IOrderPayMapService {

	public OrderPayMapService() {
		super();
		this.type = OrderPayMap.class;
	}

	@Override
	public List<OrderPayMap> queryByOrderId(Integer orderId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}
		return this.queryList(oql);
	}
}