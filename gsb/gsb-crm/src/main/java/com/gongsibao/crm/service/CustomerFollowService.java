package com.gongsibao.crm.service;

import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerFollowService;
import com.gongsibao.entity.crm.CustomerFollow;

@Service
public class CustomerFollowService extends PersistableService<CustomerFollow> implements ICustomerFollowService {

    public CustomerFollowService(){
        super();
        this.type=CustomerFollow.class;
    }
    
	public CustomerFollow newInstance() {

		CustomerFollow entity = new CustomerFollow();
		entity.toNew();
		entity.setCreateTime(new Date());
		entity.setCreateTime(new Date());
		entity.setCreator(SessionManager.getUserName());
		entity.setCreatorId(SessionManager.getUserId());
		
		return entity;
	}
	
	public CustomerFollow save(CustomerFollow entity){
		
		entity = super.save(entity);
		
		return entity;
	}
}