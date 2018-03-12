package com.gongsibao.trade.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

public class OrderStageController {
	
	/**
	 * @Title: getSoOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param id
	 * @param: @return
	 * @return: SoOrder
	 * @throws
	 */
	public SoOrder getSoOrder(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,payablePrice,paidPrice");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
}
