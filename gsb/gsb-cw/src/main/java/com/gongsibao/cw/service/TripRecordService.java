package com.gongsibao.cw.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ITripRecordService;
import com.gongsibao.entity.cw.TripRecord;

@Service
public class TripRecordService  extends PersistableService<TripRecord> implements ITripRecordService{

	public TripRecordService() {
		super();
		this.type = TripRecord.class;
	}
}
