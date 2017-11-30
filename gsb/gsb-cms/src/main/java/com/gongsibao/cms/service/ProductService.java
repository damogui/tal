package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductService;
import com.gongsibao.entity.cms.Product;

@Service
public class ProductService extends PersistableService<Product> implements IProductService {

    public ProductService(){
        super();
        this.type=Product.class;
    }
}