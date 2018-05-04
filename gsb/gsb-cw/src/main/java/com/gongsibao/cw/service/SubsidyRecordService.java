package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ISubsidyRecordService;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.SubsidyRecord;

@Service
public class SubsidyRecordService extends PersistableService<SubsidyRecord> implements ISubsidyRecordService{

	public SubsidyRecordService() {
		super();
		this.type = SubsidyRecord.class;
	}

	@Override
	public List<SubsidyRecord> getSubsidyItem(Integer expenseId) {
		Oql oql = new Oql();
		oql.setType(SubsidyRecord.class);
		oql.setSelects("subsidyRecord.*");
		oql.setFilter("expenseId=? ");
		oql.getParameters().add("expenseId", expenseId, Types.INTEGER);
		return this.queryList(oql);
	}
	
}
