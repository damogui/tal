package com.gongsibao.report.service.customer;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.report.customer.CustomerWeekGrowView;
import com.gongsibao.report.base.customer.ICustomerWeekGrowViewService;

@Service
public class CustomerWeekGrowViewService extends GsbPersistableService<CustomerWeekGrowView> implements ICustomerWeekGrowViewService{

	public CustomerWeekGrowViewService(){
		super();
		this.type=CustomerWeekGrowView.class;
	}
}