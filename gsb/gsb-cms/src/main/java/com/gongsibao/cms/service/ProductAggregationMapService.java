package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductAggregationMapService;
import com.gongsibao.entity.cms.ProductAggregationMap;

@Service
public class ProductAggregationMapService extends PersistableService<ProductAggregationMap> implements IProductAggregationMapService {

    public ProductAggregationMapService(){
        super();
        this.type=ProductAggregationMap.class;
    }
}