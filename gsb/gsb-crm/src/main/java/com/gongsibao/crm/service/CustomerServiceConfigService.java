package com.gongsibao.crm.service;

import java.sql.Types;

import org.netsharp.autoencoder.core.EncoderManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.EncrypUtil;

import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.crm.CustomerServiceConfig;

@Service
public class CustomerServiceConfigService extends PersistableService<CustomerServiceConfig> implements ICustomerServiceConfigService{

    public CustomerServiceConfigService(){
        super();
        this.type=CustomerServiceConfig.class;
    }
    
    @Override
	public CustomerServiceConfig save(CustomerServiceConfig entity) {

    	if(entity.getEntityState() != EntityState.Deleted){
    		
    		entity.setSwtServiceIdMD5(EncrypUtil.md5(entity.getSwtServiceId()));
    	}
    	
    	entity = super.save(entity);
		return entity;
	}

	@Override
	public CustomerServiceConfig bySwtServiceId(String swtServiceIdMd5) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("CustomerServiceConfig.*,CustomerServiceConfig.employee.*");
			oql.setFilter("swtServiceIdMD5=?");
			oql.getParameters().add("swtServiceIdMd5", swtServiceIdMd5, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}
