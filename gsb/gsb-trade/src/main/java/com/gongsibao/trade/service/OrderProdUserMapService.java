package com.gongsibao.trade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatusType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.trade.base.IOrderProdUserMapService;

@Service
public class OrderProdUserMapService extends PersistableService<OrderProdUserMap> implements IOrderProdUserMapService {

	public OrderProdUserMapService() {
		super();
		this.type = OrderProdUserMap.class;
	}

	@Override
	public int updateStatusByOrderProdId(List<Integer> orderProdIdList, Integer typeId, int newStatus, int oldStatus) {
		String orderProdIdsString = StringManager.join(",", orderProdIdList);
		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order_prod_user_map ");
			updateBuilder.set("status_id", OrderProdUserMapStatusType.getItem(newStatus).getValue());
			updateBuilder.where("order_prod_id in (" + orderProdIdsString + ") AND type_id = " + OrderProdUserMapType.getItem(typeId).getValue() + " AND status_id = " + OrderProdUserMapStatusType.getItem(oldStatus).getValue() + " ");
		}
		String sql = updateBuilder.toSQL();
		return this.pm.executeNonQuery(sql, null);
	}

	@Override
	public Map<Integer, String> getOrderUserByIds(List<Integer> pkidList, int typeId, int statusId) {
		Map<Integer, String> resMap = new HashMap<Integer, String>();
		String idString = StringManager.join(",", pkidList);
		StringBuffer sql = new StringBuffer("SELECT opum.order_prod_id 'orderProdId',u.real_name 'realName' FROM so_order_prod_user_map opum ");
		sql.append("JOIN uc_user u ON u.pkid = opum.user_id ");
		sql.append("WHERE opum.type_id=" + OrderProdUserMapType.getItem(typeId).getValue() + " AND opum.status_id=" + OrderProdUserMapStatusType.getItem(statusId).getValue() + " AND opum.order_prod_id IN(" + idString + ") ");
		DataTable executeTable = this.pm.executeTable(sql.toString(), null);
		for (Row row : executeTable) {
			Integer orderProdId = row.getInteger("orderProdId");
			String realName = row.getString("realName");
			resMap.put(orderProdId, realName);
		}
		return resMap;
	}

	@Override
	public Map<Integer, String> getLastOperatorByOrderIdsStatusType(List<Integer> orderIdList, Integer typeId, Integer statusId) {
		StringBuffer sql = new StringBuffer();
		Map<Integer, String> resMap = new HashMap<Integer, String>();

		if (CollectionUtils.isEmpty(orderIdList)) {
			return resMap;
		}

		String orderIds = StringManager.join(",", orderIdList);

		sql.append("SELECT od.order_id,u.`real_name` FROM uc_user u ");
		sql.append("JOIN (SELECT * FROM so_order_prod_user_map WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_user_map WHERE status_id=" + statusId + " AND type_id = " + typeId + " GROUP BY order_prod_id)) odum ON u.`pkid`=odum.user_id ");
		sql.append("JOIN so_order_prod od ON od.`pkid`=odum.`order_prod_id` ");
		sql.append("WHERE od.order_id IN(" + orderIds + ") ");
		sql.append("GROUP BY od.order_id ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);
		for (Row row : executeTable) {
			resMap.put(row.getInteger("order_id"), row.getString("real_name"));
		}

		return resMap;
	}

}