package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.trade.base.IOrderProdService;

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

			List<Map<String, Object>> tempList = valueMapList.stream().filter(x -> x.get("orderId").equals(orderId)).collect(Collectors.toList());
			String prodNames = "";
			if (resMap.get(orderId) == null) {
				for (Map<String, Object> tempMap : tempList) {
					String prodName = String.valueOf(tempMap.get("productName") == null ? "" : tempMap.get("productName"));
					String cityName = String.valueOf(tempMap.get("cityName") == null ? "" : tempMap.get("cityName"));
					if (StringManager.isNullOrEmpty(prodName) || StringManager.isNullOrEmpty(cityName))
						continue;
					// 已经放入的产品名称
					String productName = resMap.get(orderId) == null ? "" : resMap.get(orderId);
					if (productName.indexOf(prodName) > -1)
						continue;
					long count = tempList.stream().filter(x -> x.get("productName").equals(prodName) && x.get("cityName").equals(cityName)).count();
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
}