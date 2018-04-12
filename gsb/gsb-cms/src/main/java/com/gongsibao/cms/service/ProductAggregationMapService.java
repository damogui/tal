package com.gongsibao.cms.service;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductAggregationMapService;
import com.gongsibao.entity.cms.ProductAggregationMap;
import org.netsharp.util.StringManager;

import java.util.*;

@Service
public class ProductAggregationMapService extends PersistableService<ProductAggregationMap> implements IProductAggregationMapService {

    public ProductAggregationMapService(){
        super();
        this.type=ProductAggregationMap.class;
    }

    @Override
    public ProductAggregationMap findByProdProductId(int prodProductId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("prodProductId = "+prodProductId);
        }
        return this.queryFirst(oql);
    }

    @Override
    public List<ProductAggregationMap> getByCmsProdId(Integer cmsProdId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("cmsProductId = "+cmsProdId);
        }
        return this.queryList(oql);
    }

    @Override
    public List<ProductAggregationMap> getByAggregationId(Integer aggregationId) {
        Oql oql = new Oql();
        {
            //SELECT DISTINCT pam.* FROM `cms_product_aggregation_map` pam JOIN cms_product cp ON pam.prod_product_id=cp.product_id WHERE pam.`cms_product_aggregation_id` = ? AND cp.is_show=1 AND cp.status=1
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("cmsProductAggregationId = "+aggregationId);
        }
        return this.queryList(oql);
    }

    @Override
    public Map<Integer, List<ProductAggregationMap>> findMapByAggregationIds(Collection<Integer> aggregationIds) {
        Map<Integer, List<ProductAggregationMap>> result = new HashMap<>();
        if (CollectionUtils.isEmpty(aggregationIds)) {
            return result;
        }
        Oql oql = new Oql();
        {
            String isList = StringManager.join(",", Arrays.asList(aggregationIds.toArray()));
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("cmsProductAggregationId in   (" + isList + ")");
        }
        List<ProductAggregationMap> aggregationMapList = this.queryList(oql);
        for (ProductAggregationMap cmsProductAggregationMap : aggregationMapList) {
            Integer aggregationId = cmsProductAggregationMap.getCmsProductAggregationId();
            List<ProductAggregationMap> maps = result.get(aggregationId);
            if (null == maps) {
                maps = new ArrayList<>();
                result.put(aggregationId, maps);
            }
            maps.add(cmsProductAggregationMap);
        }
        return result;
    }
}