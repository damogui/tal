package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserConsStatisticView;
import com.gongsibao.taurus.base.IUserConsStatisticViewService;

@Service
public class UserConsStatisticViewService extends GsbPersistableService<UserConsStatisticView> implements IUserConsStatisticViewService{

	public UserConsStatisticViewService(){
		super();
		this.type=UserConsStatisticView.class;
	}
}
