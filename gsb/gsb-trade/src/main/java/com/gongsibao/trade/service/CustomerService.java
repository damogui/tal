package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.crm.dic.ConsultWay;
import com.gongsibao.entity.crm.dic.FollowStatus;
import com.gongsibao.entity.crm.dic.Important;
import com.gongsibao.entity.crm.dic.Sex;
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

	@Override
	public Customer byMobile(String mobile) {
		Oql oql = new Oql();
		{
			oql.setType (this.type);
			oql.setSelects ("*");
			oql.setFilter ("mobile = ? ");
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return super.queryFirst (oql);
	}

	@Override
	public Customer saveByAccount(Account account, Integer customerSourceId) {
		Customer customer = byAccountId(account.getId());
		if (null == customer) {
			customer = byMobile(account.getMobilePhone());
		}
		if (null == customer) {
			customer = new Customer();
			{
				customer.toNew();
				customer.setAccountId(account.getId());
				customer.setRealName(account.getRealName());
				customer.setMobile(account.getMobilePhone());
				customer.setEmail(account.getEmail());
				customer.setSex(Sex.SECRECY);
				customer.setTelephone("");
				customer.setQq("");
				customer.setWeixin("");

				customer.setAddr("");
				customer.setCityId(0);
				customer.setFollowUserId(0);
				customer.setFollowStatus(FollowStatus.FOLLOW_STATUS_1);
				customer.setUnvalidRemark("");

				customer.setLastFollowTime(new Date());
				customer.setBackNum(0);
				customer.setCustomerSourceId(customerSourceId);
				customer.setConsultWay(ConsultWay.CONSULT_WAY_4219);
				customer.setImportant(Important.COMMON);

				customer.setIntroducerUserId(0);
				customer.setIntroducerId(0);
				customer.setRemark("");
				customer.setCreatorId(0);
				customer.setUpdatorId(0);
			}
			// 保存客户
			customer = save(customer);
		}
		return customer;
	}
}
