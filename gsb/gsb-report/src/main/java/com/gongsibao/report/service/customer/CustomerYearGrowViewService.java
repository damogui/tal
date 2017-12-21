package com.gongsibao.report.service.customer;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.report.customer.CustomerYearGrowView;
import com.gongsibao.report.base.customer.ICustomerYearGrowViewService;

@Service
public class CustomerYearGrowViewService extends GsbPersistableService<CustomerYearGrowView> implements ICustomerYearGrowViewService{

	public CustomerYearGrowViewService(){
		super();
		this.type=CustomerYearGrowView.class;
	}
}
