package com.gongsibao.supplier.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.supplier.DepartmentServiceProduct;
import com.gongsibao.supplier.base.IDepartmentProductService;

/**
 * Created by win on 2018/1/26.
 */

@Service
public class DepartmentProductService  extends PersistableService<DepartmentServiceProduct> implements IDepartmentProductService {

    public DepartmentProductService() {
        super();
        this.type = DepartmentServiceProduct.class;
    }
}
