package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.ActiveUserView;
import com.gongsibao.taurus.base.IActiveUserViewService;

@Service
public class ActiveUserViewService extends GsbPersistableService<ActiveUserView> implements IActiveUserViewService{

	public ActiveUserViewService(){
		super();
		this.type=ActiveUserView.class;
	}
}
