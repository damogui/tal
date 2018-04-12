package com.gongsibao.cms.service;

import com.gongsibao.cms.base.IProductAggregationMapService;
import com.gongsibao.cms.base.IProductAggregationService;
import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.cms.AggregationResponse;
import com.gongsibao.entity.cms.ProductAggregation;
import com.gongsibao.entity.cms.ProductAggregationMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductService;
import com.gongsibao.entity.cms.Product;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProductService extends PersistableService<Product> implements IProductService {
    IProductAggregationMapService cmsProductAggregationMapService= ServiceFactory.create(IProductAggregationMapService.class);
    IProductAggregationService cmsProductAggregationService= ServiceFactory.create(IProductAggregationService.class);
    public ProductService(){
        super();
        this.type=Product.class;
    }

    @Override
    public List<Product> getOnlineByProductIds(Collection<Integer> productIds) {
        Oql oql = new Oql();
        {
            String isList = StringManager.join(",", Arrays.asList(productIds.toArray()));
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("productId in   (" + isList + ")");
        }
        return this.queryList(oql);
    }

    @Override
    public  Product findLastByProductId(int productId) {
        Product cmsProduct = getLastCmsDataByProdId(productId);
        if (null == cmsProduct) {
            return cmsProduct;
        }
         return cmsProduct;
    }

    @Override
    public Product getLastCmsDataByProdId(Integer productId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("productId = "+productId+" AND isShow = 1 AND status = 1 AND cooperationCompanyId = 508001  AND attributeCategory=0");
            oql.setOrderby("id DESC");
        }
        return this.queryFirst(oql);
    }

    @Override
    public AggregationResponse findAggregationByProductId(int productId) {

        AggregationResponse response = new AggregationResponse();

        ProductAggregationMap aggregationMap = cmsProductAggregationMapService.findByProdProductId(productId);
        if (null == aggregationMap) {
            return response;
        }

        int aggregationId = aggregationMap.getCmsProductAggregationId();

        // 无一级聚合
        if (aggregationId == 0) {
            Integer cmsProductId = aggregationMap.getCmsProductId();

            // 查询子聚合
            List<ProductAggregationMap> aggregationMapList = cmsProductAggregationMapService.getByCmsProdId(cmsProductId);

            List<Integer> productIds = new ArrayList<>();
            for (ProductAggregationMap cmsProductAggregationMap : aggregationMapList) {
                productIds.add(cmsProductAggregationMap.getProdProductId());
            }

            // 排查已下线产品
            List<Product> onlineProductList = getOnlineByProductIds(productIds);
            List<Integer> onlineProductIds = onlineProductList.stream().map(Product::getProductId).collect(Collectors.toList());

            Iterator<ProductAggregationMap> iterator = aggregationMapList.iterator();
            if (iterator.hasNext()) {
                ProductAggregationMap next = iterator.next();
                if (!onlineProductIds.contains(next.getProdProductId())) {
                    iterator.remove();
                }
            }
            response.setType(2);
            response.setProductAggregationMapList(null == aggregationMapList ? new ArrayList<>() : aggregationMapList);
            response.setFirstAggregationName("选择服务");
            response.setSecondAggregationName("选择服务");

        } else {
            // 有一级聚合
            // 查询一级聚合
            ProductAggregation aggregation = cmsProductAggregationService.byId(aggregationId);

            // 通过一级聚合查出同级
            List<ProductAggregation> aggregationList = cmsProductAggregationService.getByCmsProdId(aggregation.getCmsProductId());

            List<Integer> aggregationIds = new ArrayList<>();
            for (ProductAggregation cmsProductAggregation : aggregationList) {
                aggregationIds.add(cmsProductAggregation.getId());
            }

            // 批量查询子项
            Map<Integer, List<ProductAggregationMap>> childMap = cmsProductAggregationMapService.findMapByAggregationIds(aggregationIds);

            // 遍历出所有产品id
            List<Integer> productIds = new ArrayList<>();
            for (Map.Entry<Integer, List<ProductAggregationMap>> mapEntity : childMap.entrySet()) {
                List<ProductAggregationMap> value = mapEntity.getValue();
                if (CollectionUtils.isNotEmpty(value)) {
                    for (ProductAggregationMap amap : value) {
                        productIds.add(amap.getProdProductId());
                    }
                }
            }

            // 排查已下线产品
            List<Product> onlineProductList = getOnlineByProductIds(productIds);
            List<Integer> onlineProductIds = onlineProductList.stream().map(Product::getProductId).collect(Collectors.toList());

            for (ProductAggregation cmsProductAggregation : aggregationList) {
                List<ProductAggregationMap> mapList = childMap.get(cmsProductAggregation.getId());
                if (null != mapList) {
                    Iterator<ProductAggregationMap> iterator = mapList.iterator();
                    while (iterator.hasNext()) {
                        ProductAggregationMap next = iterator.next();
                        if (!onlineProductIds.contains(next.getProdProductId())) {
                            iterator.remove();
                        }
                    }
                }
                cmsProductAggregation.setCmsProductAggregationMapList(null == mapList ? new ArrayList<>() : mapList);
            }

            Product cmsProduct = byId(aggregation.getCmsProductId());

            // 设置类型和列表
            response.setType(1);
            response.setProductAggregationList(aggregationList);
            response.setFirstAggregationName(getDefault(cmsProduct.getFirstAggregationName(), "商品分类"));
            response.setSecondAggregationName(getDefault(cmsProduct.getSecondAggregationName(), "组织形式"));
        }
        return response;
    }
    public static String getDefault(String str, String def) {
        if (StringUtils.isBlank(str)) {
            return def;
        }
        return str.trim();
    }
}