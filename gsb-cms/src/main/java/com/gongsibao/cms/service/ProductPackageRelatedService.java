package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductPackageRelatedService;
import com.gongsibao.entity.cms.ProductPackageRelated;

@Service
public class ProductPackageRelatedService extends PersistableService<ProductPackageRelated> implements IProductPackageRelatedService {

    public ProductPackageRelatedService(){
        super();
        this.type=ProductPackageRelated.class;
    }
}