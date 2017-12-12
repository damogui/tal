package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.DayStatisticView;
import com.gongsibao.taurus.base.IDayStatisticViewService;

@Service
public class DayStatisticViewService  extends GsbPersistableService<DayStatisticView> implements IDayStatisticViewService{

	public DayStatisticViewService(){
		super();
		this.type=DayStatisticView.class;
	}
}
