package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.IOrderProdTraceService;

@Service
public class OrderProdTraceService extends PersistableService<OrderProdTrace>
		implements IOrderProdTraceService {

	public OrderProdTraceService() {
		super();
		this.type = OrderProdTrace.class;
	}

	@Override
	public List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId) {
		List<OrderProdTrace> getList = new ArrayList<OrderProdTrace>();
		
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * from so_order_prod_trace where type_id in (3151, 3153) and order_prod_id in(");
		//sqlBuilder.append("SELECT pkid from so_order_prod where order_id="+soOrderId);
		sqlBuilder.append("SELECT pkid from so_order_prod where order_id=263853");
		sqlBuilder.append(") ORDER BY add_time desc");
		
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);
		try {
			for (IRow row : dataTable) {
				String info = row.getString("info");
				OrderProdTrace getEntity = new OrderProdTrace();
				getEntity.setInfo(info);
				getEntity.setCreateTime(row.getDate("add_time"));
				getList.add(getEntity);
			}
		} catch (Exception e) {

		}
		return getList;
	}
}