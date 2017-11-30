package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductAggregationService;
import com.gongsibao.entity.cms.ProductAggregation;

@Service
public class ProductAggregationService extends PersistableService<ProductAggregation> implements IProductAggregationService {

    public ProductAggregationService(){
        super();
        this.type=ProductAggregation.class;
    }
}