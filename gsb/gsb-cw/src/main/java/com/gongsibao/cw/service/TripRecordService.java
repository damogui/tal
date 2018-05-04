package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ITripRecordService;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.TripRecord;

@Service
public class TripRecordService  extends PersistableService<TripRecord> implements ITripRecordService{

	public TripRecordService() {
		super();
		this.type = TripRecord.class;
	}

	@Override
	public List<TripRecord> getTripItem(Integer expenseId) {
		Oql oql = new Oql();
		oql.setType(TripRecord.class);
		oql.setSelects("tripRecord.*");
		oql.setFilter("expenseId=? ");
		oql.getParameters().add("expenseId", expenseId, Types.INTEGER);
		return this.queryList(oql);
	}
	
	
}
