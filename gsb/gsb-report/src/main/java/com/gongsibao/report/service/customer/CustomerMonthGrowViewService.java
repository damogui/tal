package com.gongsibao.report.service.customer;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.report.customer.CustomerMonthGrowView;
import com.gongsibao.report.base.customer.ICustomerMonthGrowViewService;

@Service
public class CustomerMonthGrowViewService extends GsbPersistableService<CustomerMonthGrowView> implements ICustomerMonthGrowViewService{

	public CustomerMonthGrowViewService(){
		super();
		this.type=CustomerMonthGrowView.class;
	}
}