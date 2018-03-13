package com.gongsibao.trade.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

public class OrderStageController {

	IOrderService orderService = ServiceFactory.create(IOrderService.class);

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
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
	
	/**
	 * @Title: applyStage
	 * @Description: TODO(申请分期)
	 * @param: @param soOrder
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean applyStage(SoOrder soOrder) {

		return orderService.applyStage(soOrder);
	}
}
