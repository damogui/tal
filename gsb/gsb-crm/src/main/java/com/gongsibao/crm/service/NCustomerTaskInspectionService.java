package com.gongsibao.crm.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskInspectionService;
import com.gongsibao.entity.crm.NCustomerTaskInspection;
import com.gongsibao.entity.trade.SoOrder;

@Service
public class NCustomerTaskInspectionService extends SupplierPersistableService<NCustomerTaskInspection> implements
INCustomerTaskInspectionService {

	public NCustomerTaskInspectionService() {
		super();
		this.type = NCustomerTaskInspection.class;
	}

	@Override
	public NCustomerTaskInspection getByTaskId(Integer taskId) {
		Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("taskId =?");
            oql.setFilter("inspectionState = 4");
            oql.getParameters().add("@taskId", taskId, Types.INTEGER);
        }
        NCustomerTaskInspection entity = super.queryFirst(oql);
		return entity;
	}
}
