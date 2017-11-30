package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductTemplateBdDictMapService;
import com.gongsibao.entity.cms.ProductTemplateBdDictMap;

@Service
public class ProductTemplateBdDictMapService extends PersistableService<ProductTemplateBdDictMap> implements IProductTemplateBdDictMapService {

    public ProductTemplateBdDictMapService(){
        super();
        this.type=ProductTemplateBdDictMap.class;
    }
}