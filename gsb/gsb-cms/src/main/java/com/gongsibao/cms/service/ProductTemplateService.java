package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductTemplateService;
import com.gongsibao.entity.cms.ProductTemplate;

@Service
public class ProductTemplateService extends PersistableService<ProductTemplate> implements IProductTemplateService {

    public ProductTemplateService(){
        super();
        this.type=ProductTemplate.class;
    }
}