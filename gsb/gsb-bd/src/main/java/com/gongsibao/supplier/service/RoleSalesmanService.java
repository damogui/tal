package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.RoleSalesman;
import com.gongsibao.supplier.base.IRoleSalesmanService;

@Service
public class RoleSalesmanService extends SupplierPersistableService<RoleSalesman> implements IRoleSalesmanService {

    public RoleSalesmanService() {
        super();
        this.type = RoleSalesman.class;
    }
}