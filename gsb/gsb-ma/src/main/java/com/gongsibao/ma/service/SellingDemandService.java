package com.gongsibao.ma.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.DemandSoldOutState;
import com.gongsibao.entity.ma.dic.PriceInterval;
import com.gongsibao.entity.ma.dic.SelingStatus;
import com.gongsibao.ma.base.ISellingDemandService;

@Service
public class SellingDemandService extends PersistableService<SellingDemand> implements ISellingDemandService {

	public SellingDemandService() {
		super();
		this.type = SellingDemand.class;
	}
	
	@Override
	public SellingDemand save(SellingDemand entity) {
		
		if(entity.getEntityState() != EntityState.Deleted){
			
			BigDecimal price = entity.getValuationPrice();
			PriceInterval priceInterval = PriceInterval.getByPrice(price);
			entity.setPriceInterval(priceInterval);
		}
		
		entity = super.save(entity);
		return entity;
	}

	@Override
	public List<SellingDemand> getMatchingList() {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("SellingDemand.*,subdiaryieCompanyDetails.*,brancheCompanyDetails.*,turnoverDetails.*,intangibleAssetss.*,fixedAssetss.*,qualificationDetails.*");
			oql.setFilter("selingStatus<>?");
			oql.getParameters().add("selingStatus", SelingStatus.BESOLD.getValue(), Types.INTEGER);
		}

		return this.queryList(oql);
	}

	@Override
	public boolean updateSelingStatus(Integer id, SelingStatus selingStatus) {
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("seling_status",selingStatus.getValue());
			updateBuilder.where("id =" +id);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
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