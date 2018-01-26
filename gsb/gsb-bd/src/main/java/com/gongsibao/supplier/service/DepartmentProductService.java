package com.gongsibao.supplier.service;

import com.gongsibao.entity.supplier.DepartmentServiceProduct;
import com.gongsibao.entity.supplier.SalesmanProduct;
import com.gongsibao.supplier.base.IDepartmentProductService;
import com.gongsibao.supplier.base.ISalesmanProductService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

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
