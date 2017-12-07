package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserRenewalStatisticView;
import com.gongsibao.taurus.base.IUserRenewalStatisticViewService;

@Service
public class UserRenewalStatisticViewService extends GsbPersistableService<UserRenewalStatisticView> implements IUserRenewalStatisticViewService{

	public UserRenewalStatisticViewService(){
		super();
		this.type=UserRenewalStatisticView.class;
	}
}
