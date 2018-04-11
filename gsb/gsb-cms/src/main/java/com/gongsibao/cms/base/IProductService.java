package com.gongsibao.cms.base;

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
}