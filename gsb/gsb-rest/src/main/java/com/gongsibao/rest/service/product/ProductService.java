package com.gongsibao.rest.service.product;

import com.gongsibao.bd.base.IBdServiceProductService;
import com.gongsibao.bd.base.IBdServiceService;
import com.gongsibao.bd.base.IFileService;
import com.gongsibao.cms.base.IProductTemplateService;
import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.bd.BdServiceProduct;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.cms.AggregationResponse;
import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;
import com.gongsibao.rest.web.common.util.ProductUtils;
import com.gongsibao.rest.web.dto.product.ProductCmsDTO;
import com.gongsibao.rest.base.product.IProductService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("productService")
public class ProductService implements IProductService {

    com.gongsibao.cms.base.IProductService cmsProductService = ServiceFactory.create(com.gongsibao.cms.base.IProductService.class);

    IProductTemplateService productTemplateService = ServiceFactory.create(IProductTemplateService.class);

    IBdServiceService bdServiceService = ServiceFactory.create(IBdServiceService.class);

    IBdServiceProductService bdServiceProductService = ServiceFactory.create(IBdServiceProductService.class);

    IFileService bdFileService = ServiceFactory.create(IFileService.class);

    @Override
    public Product getLastCmsByProdId(Integer productId) {
        return cmsProductService.getLastCmsByProdId(productId);
    }

    @Override
    public ProductTemplate getProductTemplateByCmsIdAndCityId(Integer cmsId, Integer cityId) {
        return productTemplateService.getProductTemplateByCmsIdAndCityId(cmsId, cityId);
    }

    @Override
    public List<BdService> findServiceList() {
        List<BdService> list = bdServiceService.findServiceList();
        if (CollectionUtils.isEmpty(list)) {
            return list;
        }
        // 记录serviceIds
        List<Integer> serviceIds = new ArrayList<>();
        for (BdService bdService : list) {
            serviceIds.add(bdService.getId().intValue());
        }
        // 查询商品
        Map<Integer, List<BdServiceProduct>> serviceProductMap = findProductMapByServiceIds(serviceIds);
        Iterator<BdService> serviceIterator = list.iterator();
        while (serviceIterator.hasNext()) {
            BdService bdService = serviceIterator.next();
            int serviceId = bdService.getId().intValue();
            List<BdServiceProduct> productList = serviceProductMap.get(serviceId);
            if (CollectionUtils.isEmpty(productList)) {
                serviceIterator.remove();
                continue;
            }
            bdService.setProductList(productList);
        }
        return list;
    }

    @Override
    public Map<Integer, List<BdServiceProduct>> findProductMapByServiceIds(Collection<Integer> serviceIds) {

        Map<Integer, List<BdServiceProduct>> result = new HashMap<>();
        if (CollectionUtils.isEmpty(serviceIds)) {
            return result;
        }

        List<BdServiceProduct> productList = bdServiceProductService.findByServiceIds(serviceIds);
        if (CollectionUtils.isEmpty(productList)) {
            return result;
        }

        Map<Integer, BdServiceProduct> serviceProductMap = new HashMap<>();
        for (BdServiceProduct bdServiceProduct : productList) {
            serviceProductMap.put(bdServiceProduct.getProductId(), bdServiceProduct);
        }
        // 查询线上产品id
        List<Product> cmsProductList = cmsProductService.getOnlineByProductIds(serviceProductMap.keySet());

        if (CollectionUtils.isEmpty(cmsProductList)) {
            return result;
        }

        List<Integer> productIds = new ArrayList<>();
        for (Product product : cmsProductList) {
            productIds.add(product.getId());
        }

        Map<Integer, List<File>> pcImgMap = bdFileService.getMapByFormIds("product_cms_lefttop", productIds);

        for (Product cmsProduct : cmsProductList) {
            BdServiceProduct bdServiceProduct = serviceProductMap.get(cmsProduct.getProductId());
            // 取cms产品名称
            bdServiceProduct.setName(cmsProduct.getProdName());

            int serviceId = bdServiceProduct.getServiceId();
            List<BdServiceProduct> list = result.get(serviceId);

            if (null == list) {
                list = new ArrayList<>();
                result.put(serviceId, list);
            }

            List<File> files = pcImgMap.get(cmsProduct.getId());
            if (null != files && !files.isEmpty()) {
                bdServiceProduct.setPcImg(files.get(0).getUrl());
            }

            list.add(bdServiceProduct);
        }
        return result;
    }

    @Override
    public ProductCmsDTO cmsInfo(Integer productId) {
        // 获取cms基础信息
        Product cmsProduct = cmsProductService.findLastByProductId(productId);
        if (null == cmsProduct) {
            return new ProductCmsDTO();
        }

        //PC产品图（可能有多个）
        cmsProduct.setPcProductImgList(bdFileService.getByTabNameFormId("product_cms_lefttop", cmsProduct.getId()));

        File file = bdFileService.byId(cmsProduct.getAppPordImgUrlId());
        if (null != file) {
            cmsProduct.setAppPordImgUrl(file.getUrl());
        }

        // 聚合信息查询
        AggregationResponse aggregation = cmsProductService.findAggregationByProductId(productId);

        // cms信息设置
        ProductCmsDTO dto = new ProductCmsDTO();
        {
            dto.setDescription(ProductUtils.getDescription(productId, "", 0));
            dto.setFlowList(ProductUtils.getTaskFlow(productId, 1));
            dto.setMaterialList(ProductUtils.getTaskFlow(productId, 2));
            dto.setProduct(cmsProduct);
            dto.setAggregation(aggregation);
            dto.setShowprice(cmsProduct.getShowprice());
        }
        return dto;
    }
}
