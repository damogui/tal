package com.gongsibao.supplier.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;

@Service
public class SalesmanService extends SupplierPersistableService<Salesman> implements ISalesmanService {

	public SalesmanService() {
		super();
		this.type = Salesman.class;
	}

	@Override
    public Integer getSupplierId(Integer employeeId) {

        Salesman entity = byEmployeeId(employeeId);
        if(entity != null){

            return entity.getSupplierId();
        }
        return null;
    }


    @Override
    public Salesman save(Salesman enity) {


//        Salesman entity = byEmployeeId(employeeId);
//        if(entity != null){
//
//            return entity.getSupplierId();
//        }
        return null;
    }

	@Override
	public Integer getDepartmentId(Integer employeeId) {
		
		Salesman entity = byEmployeeId(employeeId);
		if(entity != null){
			
			return entity.getDepartmentId();
		}
		return null;
	}

	@Override
	public Salesman byEmployeeId(Integer employeeId) {
		
		Oql oql = new Oql();
		{
			oql.setType(type);
			oql.setSelects("*");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
		}
		Salesman entity = this.queryFirst(oql);
		return entity;
	}
}