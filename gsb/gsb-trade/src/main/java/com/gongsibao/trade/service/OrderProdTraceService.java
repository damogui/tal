package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;

@Service
public class OrderProdTraceService extends PersistableService<OrderProdTrace> implements IOrderProdTraceService {

	public OrderProdTraceService() {
		super();
		this.type = OrderProdTrace.class;
	}

	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

	@Override
	public List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId) {
		List<OrderProdTrace> getList = new ArrayList<OrderProdTrace>();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * from so_order_prod_trace where type_id in (3151, 3153) and order_prod_id in(");
		// sqlBuilder.append("SELECT pkid from so_order_prod where order_id="+soOrderId);
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

	@Override
	public List<OrderProdTrace> getByOrderIdAndType(List<Integer> orderIdList, Integer type) {
		List<OrderProdTrace> resList = new ArrayList<OrderProdTrace>();
		List<Integer> orderProdIdList = orderProdService.getIdsByOrderIds(orderIdList);

		if (CollectionUtils.isEmpty(orderProdIdList)) {
			return resList;
		}

		String orderProdIds = StringManager.join(",", orderProdIdList);

		String typeWhere = type.equals(-1) ? "" : " AND type = " + type + " ";

		StringBuilder builder = new StringBuilder();
		builder.append("OrderProdTrace.*,");
		builder.append("OrderProdTrace.allocationOrg.*,");
		builder.append("Customer.prodDetails.*,");

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderProdId in (" + orderProdIds + ") " + typeWhere + "");
			oql.setOrderby("createTime DESC");
		}
		resList = this.pm.queryList(oql);

		return resList;
	}

	@Override
	public Map<Integer, String> getLastInfoByOrderIdType(List<Integer> orderIdList, Integer type) {

		Map<Integer, String> resMap = new HashMap<Integer, String>();

		if (CollectionUtils.isEmpty(orderIdList)) {
			return resMap;
		}

		String orderIds = StringManager.join(",", orderIdList);

		String typeWhere = type.equals(-1) ? "" : " AND odt.type_id = " + type + " ";

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT od.`order_id`,odt.info,odt.add_time FROM so_order_prod od ");
		sql.append("JOIN (SELECT * FROM so_order_prod_trace WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_trace GROUP BY order_prod_id)) odt ON odt.order_prod_id = od.`pkid` ");
		sql.append("WHERE od.order_id IN(" + orderIds + ") " + typeWhere + " ");
		sql.append("GROUP BY od.order_id ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);

		for (Row row : executeTable) {
			resMap.put(row.getInteger("order_id"), row.getString("info") + "-跟进时间：" + row.getDate("add_time"));
		}

		return resMap;
	}

}