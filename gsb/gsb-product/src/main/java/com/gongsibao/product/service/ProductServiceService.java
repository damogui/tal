package com.gongsibao.product.service;

import org.netsharp.service.PersistableService;

import com.gongsibao.product.base.IProductServiceService;

@org.netsharp.communication.Service
public class ProductServiceService extends PersistableService<com.gongsibao.entity.product.ProductService> implements IProductServiceService {

    public ProductServiceService(){
        super();
        this.type=com.gongsibao.entity.product.ProductService.class;
    }
}