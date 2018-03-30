package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.trade.base.ICustomerService;

@Service
public class CustomerService extends PersistableService<Customer> implements ICustomerService {

	public CustomerService() {
		super();
		this.type = Customer.class;
	}
	
	@Override
	public Map<Integer, String> getCustomerNameByOrderIdList(List<Integer> orderIdList) {
		StringBuffer sql = new StringBuffer();
		Map<Integer, String> resMap = new HashMap<Integer, String>();

		if (CollectionUtils.isEmpty(orderIdList)) {
			return resMap;
		}

		String orderIds = StringManager.join(",", orderIdList);

		sql.append("SELECT oi.`pkid` 'orderId',c.`real_name`  FROM so_order oi   ");
		sql.append("LEFT JOIN crm_customer c ON oi.`account_id` = c.`account_id` ");
		sql.append("WHERE oi.`pkid` IN(" + orderIds + ")  ");
		sql.append("GROUP BY oi.`pkid`   ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);
		for (Row row : executeTable) {
			resMap.put(row.getInteger("orderId"), row.getString("real_name"));
		}
		return resMap;
	}

	@Override
	public Customer byAccountId(int accountId) {
		Oql oql = new Oql();
		{
			oql.setType (this.type);
			oql.setSelects ("*");
			oql.setFilter ("accountId = ? ");
			oql.getParameters().add("accountId", accountId, Types.INTEGER);
		}
		return super.queryFirst (oql);
	}
}
