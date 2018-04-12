package com.gongsibao.cms.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cms.ProductAggregationMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IProductAggregationMapService extends IPersistableService<ProductAggregationMap> {
    public ProductAggregationMap findByProdProductId(int prodProductId);
    public List<ProductAggregationMap> getByCmsProdId(Integer cmsProdId);
    /*根据聚合id获取该聚合信息下所有聚合产品信息*/
    List<ProductAggregationMap> getByAggregationId(Integer aggregationId);
    Map<Integer, List<ProductAggregationMap>> findMapByAggregationIds(Collection<Integer> aggregationIds);
}