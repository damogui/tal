package com.gongsibao.ma.service;

import org.netsharp.communication.Service;
import org.netsharp.core.MtableManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.dic.DemandSoldOutState;
import com.gongsibao.ma.base.IAcquisitionDemandService;

@Service
public class AcquisitionDemandService extends PersistableService<AcquisitionDemand> implements IAcquisitionDemandService {

	public AcquisitionDemandService() {
		
		super();
		this.type = AcquisitionDemand.class;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean changeSoldOutState(int id, DemandSoldOutState soldOutState) {
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("sold_out_state",soldOutState.getValue());
			updateBuilder.where("id =" +id);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}
}
