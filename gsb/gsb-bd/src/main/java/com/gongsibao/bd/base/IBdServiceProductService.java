package com.gongsibao.bd.base;

import com.gongsibao.entity.bd.BdServiceProduct;
import org.netsharp.base.IPersistableService;

import java.util.Collection;
import java.util.List;

public interface IBdServiceProductService extends IPersistableService<BdServiceProduct> {
    /**
     * 根据服务id 查询商品列表
     * @param serviceIds
     * @return
     */
    public List<BdServiceProduct> findByServiceIds(Collection<Integer> serviceIds);

    /**
     * 根据服务id 查询产品id
     * @param serviceId
     * @return
     */
    public List<Integer> findProductIdsByServiceId(int serviceId);

    /**
     * 根据服务id 查询商品列表
     * @param serviceIds
     * @param hot  默认-1
     * @return
     */
    public List<BdServiceProduct> findByServiceIds(Collection<Integer> serviceIds, int hot);
}
