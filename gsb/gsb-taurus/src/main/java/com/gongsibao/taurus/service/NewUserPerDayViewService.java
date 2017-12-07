package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.NewUserPerDayView;
import com.gongsibao.taurus.base.INewUserPerDayViewService;

@Service
public class NewUserPerDayViewService extends
		PersistableService<NewUserPerDayView> implements
		INewUserPerDayViewService {

	public NewUserPerDayViewService() {
		super();
		this.type = NewUserPerDayView.class;
	}

}
