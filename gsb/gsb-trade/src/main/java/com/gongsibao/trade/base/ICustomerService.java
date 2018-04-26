package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import com.gongsibao.entity.acount.Account;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.Customer;
import org.netsharp.core.annotations.Transaction;

public interface ICustomerService extends IPersistableService<Customer> {
	
	Map<Integer, String> getCustomerNameByOrderIdList(List<Integer> orderIdList);

	Customer byAccountId(int accountId);

	Customer byMobile(String mobile);

	@Transaction
	Customer saveByAccount(Account account, Integer customerSource);
}

