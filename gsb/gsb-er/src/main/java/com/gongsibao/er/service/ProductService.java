package com.gongsibao.er.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.er.Product;
import com.gongsibao.er.base.IProductService;

@Service
public class ProductService extends PersistableService<Product> implements IProductService {

    public ProductService(){
        super();
        this.type=Product.class;
    }
}