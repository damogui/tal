package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductRelatedService;
import com.gongsibao.entity.cms.ProductRelated;

@Service
public class ProductRelatedService extends PersistableService<ProductRelated> implements IProductRelatedService {

    public ProductRelatedService(){
        super();
        this.type=ProductRelated.class;
    }
}