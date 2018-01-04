package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.Customer;

public interface ICustomerService extends IPersistableService<Customer> {
	
	Map<Integer, String> getCustomerNameByOrderIdList(List<Integer> orderIdList);
}

