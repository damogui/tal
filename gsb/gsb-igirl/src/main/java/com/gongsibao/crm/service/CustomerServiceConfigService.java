package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.crm.CustomerServiceConfig;
import com.gongsibao.entity.crm.dic.ServiceType;

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
			oql.setFilter("swtServiceIdMD5=? and employee.disabled = 0");
			oql.getParameters().add("swtServiceIdMd5", swtServiceIdMd5, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}

	@Override
	public ServiceType getTypeByEmployeeId(Integer employeeId) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("id,type");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("employeeId", employeeId, Types.INTEGER);
		}
		CustomerServiceConfig config =  this.queryFirst(oql);
		if(config == null){
			
			return null;
		}
		return config.getType();
	}

	@Override
	public boolean updateLastUseDate(Integer employeeId, Date useDate) {

		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
			updateBuilder.set("use_date", useDate);
			updateBuilder.where("employee_id =" +employeeId);
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}
}
