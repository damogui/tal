package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.dic.SettleStatus;
import com.gongsibao.trade.base.IOrderProdService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.*;
import org.netsharp.service.PersistableService;
import org.netsharp.util.NumUtil;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProdService extends PersistableService<OrderProd> implements IOrderProdService {

	public OrderProdService() {
		super();
		this.type = OrderProd.class;
	}

	@Override
	public Map<Integer, String> getProductCityNameByOrderIds(List<Integer> orderIdList) {
		Map<Integer, String> resMap = new HashMap<Integer, String>();
		String orderIds = StringManager.join(",", orderIdList);
		if (StringManager.isNullOrEmpty(orderIds)) {
			return resMap;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT `getbddictfullname`(od.`city_id`) 'cityName',od.product_name 'productName',od.order_id 'orderId' FROM so_order_prod od ");
		sql.append("JOIN  so_order oi ON od.order_id = oi.pkid ");
		sql.append("WHERE od.order_id IN (" + orderIds + ") ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);

		List<Map<String, Object>> valueMapList = executeTable.getValueMapList();

		for (IRow row : executeTable) {
			Integer orderId = row.getInteger("orderId");

			
			List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();

			for (Map<String, Object> map : valueMapList) {
				Integer oid = NumUtil.parseInteger(map.get("orderId") == null ? "0" : map.get("orderId").toString());
				if (orderId.equals(oid)) {
					tempList.add(map);
				}
			}

			String prodNames = "";
			if (resMap.get(orderId) == null) {
				for (Map<String, Object> tempMap : tempList) {
					String prodName = String.valueOf(tempMap.get("productName") == null ? "" : tempMap.get("productName"));
					String cityName = String.valueOf(tempMap.get("cityName") == null ? "" : tempMap.get("cityName"));
					if (StringManager.isNullOrEmpty(prodName) || StringManager.isNullOrEmpty(cityName))
						continue;
					// 已经放入的产品名称
					if (prodNames.indexOf(prodName) > -1)
						continue;
					int count = 0;
					for (Map<String, Object> map : tempList) {
						String tempProdName = String.valueOf(map.get("productName") == null ? "" : map.get("productName"));
						String tempCityName = String.valueOf(map.get("cityName") == null ? "" : map.get("cityName"));
						if (tempProdName.equals(prodName) && tempCityName.equals(tempCityName)) {
							count = count + 1;
						}
					}

					prodNames += prodName + "*" + count + "|" + cityName + ",";
					resMap.put(orderId, prodNames.substring(0, prodNames.length() - 1));
				}
				resMap.put(orderId, prodNames.substring(0, prodNames.length() - 1));
			}
		}

		return resMap;
	}

	@Override
	public List<Integer> getIdsByOrderIds(List<Integer> orderIdList) {
		List<Integer> idList = new ArrayList<Integer>();
		String orderIds = StringManager.join(",", orderIdList);
		StringBuffer sql = new StringBuffer("SELECT pkid from so_order_prod where order_id in (" + orderIds + ")");
		DataTable executeTable = this.pm.executeTable(sql.toString(), null);
		for (Row row : executeTable) {
			idList.add(row.getInteger("pkid"));
		}
		return idList;
	}

	@Override
	public int updateAssignByIds(Integer isAssign, List<Integer> pkidList) {

		if (CollectionUtils.isEmpty(pkidList)) {
			return 0;
		}

		String idsString = StringManager.join(",", pkidList);

		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order_prod");
			updateBuilder.set("is_assign", isAssign);
			updateBuilder.where("pkid in(" + idsString + ")");
		}
		String sql = updateBuilder.toSQL();
		return this.pm.executeNonQuery(sql, null);
	}

	@Override
	public List<OrderProd> queryByOrderId(Integer orderId) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("OrderProd.*,OrderProd.owner.{id,name},OrderProd.items.{id,serviceName}");
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public boolean updateSettleStatus(List<Integer> orderProdIds, SettleStatus settleStatus) {
		if (null == orderProdIds || orderProdIds.isEmpty()) {
			return false;
		}

		if (null == settleStatus) {
			return false;
		}
		UpdateBuilder build = UpdateBuilder.getInstance();
		build.update(MtableManager.getMtable(this.type).getTableName());
		build.set("settle_status", settleStatus.getValue());
		build.where("pkid IN ( " + StringManager.join(",", orderProdIds) + " ) ");
		return this.pm.executeNonQuery(build.toSQL(), null) > 0;
	}
}