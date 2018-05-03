package com.gongsibao.igirl.tm.service;


import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.baseinfo.NclBatch;
import com.gongsibao.entity.igirl.tm.baseinfo.NclMap;
import com.gongsibao.igirl.tm.base.INclBatchService;
import com.gongsibao.igirl.tm.base.INclMapService;
@Service
public class NclMapService extends GsbPersistableService<NclMap> implements INclMapService {
	INclBatchService nb = ServiceFactory.create(INclBatchService.class);
	public NclMapService() {
		super();
		this.type = NclMap.class;
	}

	@Override
	public NclMap newInstance() {
		NclMap nm = super.newInstance();
		Oql oql=new Oql();{
			oql.setType(NclBatch.class);
			oql.setSelects("NclBatch.*");
			oql.setFilter("currentStatus=?");
			oql.getParameters().add("currentStatus",true,Types.BOOLEAN);
		}
		NclBatch tmp=nb.queryFirst(oql);
		nm.setNclBatch(tmp);
		nm.setNclBeachId(tmp.getId());
		return nm;

	}

	public String getTmplByNclOneId(int nclOneId){
		NclMap nm = super.newInstance();
		Oql oql=new Oql();{
			oql.setType(NclBatch.class);
			oql.setSelects("NclBatch.*");
			oql.setFilter("currentStatus=?");
			oql.getParameters().add("currentStatus",true,Types.BOOLEAN);
		}
		NclBatch tmp=nb.queryFirst(oql);
		oql=new Oql();{
			oql.setType(NclMap.class);
			oql.setSelects("NclMap.*");
			oql.setFilter("nclOneId=? and nclBeachId=? ");
			oql.getParameters().add("nclOneId",nclOneId,Types.INTEGER);
			oql.getParameters().add("nclBeachId",tmp.getId(),Types.INTEGER);
		}
		nm = this.queryFirst(oql);
		if(nm==null){
			return "";
		}
		return nm.getNclTwoContent();
	}
}
