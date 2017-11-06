package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.ProductBusiness;
import com.gongsibao.product.base.IProductBusinessService;

@Service
public class ProductBusinessService extends PersistableService<ProductBusiness> implements IProductBusinessService {

    public ProductBusinessService(){
        super();
        this.type=ProductBusiness.class;
    }
}