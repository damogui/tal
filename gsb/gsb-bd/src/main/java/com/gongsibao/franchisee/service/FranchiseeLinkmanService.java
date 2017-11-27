package com.gongsibao.franchisee.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.franchisee.base.IFranchiseeLinkmanService;

@Service
public class FranchiseeLinkmanService extends PersistableService<FranchiseeLinkman> implements IFranchiseeLinkmanService {

    public FranchiseeLinkmanService(){
        super();
        this.type=FranchiseeLinkman.class;
    }

	@Override
	public List<FranchiseeLinkman> getLinkmanByFranchiseeId(Integer franchiseeId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("franchiseeId=?");
			oql.setOrderby("main Desc");
			oql.getParameters().add("franchiseeId", franchiseeId, Types.INTEGER);
		}
		return this.queryList(oql);
	}
}