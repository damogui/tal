package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerFollow;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.dic.FollowStatus;
import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

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
			INCustomerTaskService customerService = ServiceFactory.create(INCustomerTaskService.class);
			NCustomerTask customer = customerService.byId(entity.getCustomerId());
			// 回写最新的跟进状态和下次跟进时间等内容
			if (customer!=null) {
				//customer.setFoolowStatus(entity.getFoolowStatus());
				customer.setIntentionCategory(entity.getQualityCategory());
				customer.setLastContent(entity.getContent());
				customer.setNextFoolowTime(entity.getNextFoolowTime());
				customer.setLastFoolowUserId(entity.getUpdatorId());
				customerService.save(customer);
			}
		}
		entity = super.save(entity);
		return entity;
	}
}