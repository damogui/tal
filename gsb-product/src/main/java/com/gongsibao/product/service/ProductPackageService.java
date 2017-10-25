package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.ProductPackage;
import com.gongsibao.product.base.IProductPackageService;

@Service
public class ProductPackageService extends PersistableService<ProductPackage> implements IProductPackageService {

    public ProductPackageService(){
        super();
        this.type=ProductPackage.class;
    }
}