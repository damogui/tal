package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.supplier.base.ISalesmanRoleService;

@Service
public class SalesmanRoleService extends SupplierPersistableService<SalesmanRole> implements ISalesmanRoleService {

    public SalesmanRoleService() {
        super();
        this.type = SalesmanRole.class;
    }
}