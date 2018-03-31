package com.gongsibao.cw.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ISubsidyRecordService;
import com.gongsibao.entity.cw.SubsidyRecord;

@Service
public class SubsidyRecordService extends PersistableService<SubsidyRecord> implements ISubsidyRecordService{

	public SubsidyRecordService() {
		super();
		this.type = SubsidyRecord.class;
	}
}
