package com.gongsibao.crm.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;

@Service
public class NCustomerTaskFoolowService extends
		SupplierPersistableService<NCustomerTaskFoolow> implements
		INCustomerTaskFoolowService {

	public NCustomerTaskFoolowService() {
		super();
		this.type = NCustomerTaskFoolow.class;
	}

	public NCustomerTaskFoolow save(NCustomerTaskFoolow entity) {
		
		if (entity.getEntityState() == EntityState.New) {

			ActionContext ctx = new ActionContext();
			{
				ctx.setPath("gsb/crm/task/follow");
				ctx.setItem(entity);
				ctx.setState(entity.getEntityState());
			}
			ActionManager action = new ActionManager();
			action.execute(ctx);
			
			entity = (NCustomerTaskFoolow) ctx.getItem();
		}else{

			entity = super.save(entity);
		}
		return entity;
	}
}