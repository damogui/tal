package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.web.dto.PayPerformanceDTO;

public class PayPerformanceController {

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
			oql.setSelects("SoOrder.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}

	public Boolean applyPayPerformance(PayPerformanceDTO dto) {

		INDepPayService service = ServiceFactory.create(INDepPayService.class);
		List<NDepPay> depPayList =  dto.getDepPayList();
		return service.applyPayPerformance(depPayList);
	}
}
