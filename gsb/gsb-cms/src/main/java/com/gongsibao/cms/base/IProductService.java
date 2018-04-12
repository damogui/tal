package com.gongsibao.cms.base;

import com.gongsibao.entity.cms.AggregationResponse;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cms.Product;

import java.util.Collection;
import java.util.List;

public interface IProductService extends IPersistableService<Product> {
    /**
     * 根据cms产品id查询产品列表
     * @param productIds
     * @return
     */
    public List<Product> getOnlineByProductIds(Collection<Integer> productIds);

    Product findLastByProductId(int productId);

    /**
     * @Description:TODO 根据产品id获取，该产品的最新显示且发布的cms信息
     * @param
     * @return
     * @author bhpeng <bhpeng@gongsibao.com>
     * @date 2018/4/11 19:36
     */
    Product getLastCmsDataByProdId(Integer productId);

    AggregationResponse findAggregationByProductId(int productId);
}