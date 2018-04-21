package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderDiscount;
import com.gongsibao.trade.base.IOrderDiscountService;

@Service
public class OrderDiscountService extends PersistableService<OrderDiscount> implements IOrderDiscountService {

	public OrderDiscountService() {
		super();
		this.type = OrderDiscount.class;
	}

	@Override
	public List<OrderDiscount> queryByOrderId(Integer orderId) {
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrderDiscount.*,preferential.{id,creator}");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public int updateNo(Integer pkid, String no) {
		String sql = String.format("UPDATE so_order_discount SET `no` = %s WHERE pkid = %s ",pkid,no);
		return this.pm.executeNonQuery(sql,null);
	}
}