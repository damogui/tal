package com.gongsibao.rest.service.product.impl;

import com.gongsibao.cms.base.IProductService;
import com.gongsibao.cms.base.IProductTemplateService;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import com.gongsibao.rest.service.product.IProService;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class ProService implements IProService {
    IProductService productService= ServiceFactory.create(IProductService.class);
    IProductTemplateService templateService=ServiceFactory.create(IProductTemplateService.class);
    @Override
    public Product getLastCmsByProdId(Integer productId) {
        return productService.getLastCmsByProdId(productId);
    }

    @Override
    public ProductTemplate getProductTemplateByCmsIdAndCityId(Integer cmsId, Integer cityId) {
        return templateService.getProductTemplateByCmsIdAndCityId(cmsId,cityId);
    }
}
