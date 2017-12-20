package com.gongsibao.report.service.customer;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.report.customer.CustomerDayGrowView;
import com.gongsibao.report.base.customer.ICustomerDayGrowViewService;

@Service
public class CustomerDayGrowViewService extends GsbPersistableService<CustomerDayGrowView> implements ICustomerDayGrowViewService{

	public CustomerDayGrowViewService(){
		super();
		this.type=CustomerDayGrowView.class;
	}
}
