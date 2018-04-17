package com.gongsibao.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.Salesman;

@Service
public class SalesmanService extends PersistableService<Salesman> implements ISalesmanService {

    public SalesmanService() {
        super();
        this.type = Salesman.class;
    }

    @Override
    public boolean getReceiving(Integer employeeId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("id,receiving");
            oql.setFilter("employeeId=?");
            oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
        }
        Salesman entity = this.queryFirst(oql);
        if(entity == null){
        	
        	return false;
        }
        return entity.getReceiving();
    }

}
