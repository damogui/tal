package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.uc.Auth;
import com.gongsibao.uc.base.IAuthService;

@Service
public class AuthService extends PersistableService<Auth> implements IAuthService {

    public AuthService(){
        super();
        this.type=Auth.class;
    }
    
	@Override
	public Auth save(Auth entity) {

		EntityState entityState = entity.getEntityState();
		if(entityState != EntityState.Deleted){
			
			//entity.setPid(entity.getParentId());
		}
		entity = super.save(entity);
		
		if(entityState != EntityState.Deleted){

			this.updateId(entity);
		}
		return entity;
	}
	
	public boolean updateId(Auth entity) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("id", entity.getId());
			//updateBuilder.set("pid", entity.getParentId());
			updateBuilder.where("pkid =" +entity.getPkid());
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}
}