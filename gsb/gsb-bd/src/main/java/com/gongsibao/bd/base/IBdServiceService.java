package com.gongsibao.bd.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.BdService;
import com.gongsibao.entity.bd.BdServiceProduct;

public interface IBdServiceService extends IPersistableService<BdService>{
    /**
     * 查询服务列表
     * @return
     */
    List<BdService> findServiceList();

    /**
     * 查询产品列表
     * @param serviceId
     * @return
     */
    List<BdServiceProduct> findProductList(int serviceId);

    /**
     * 根据服务id 和 热度查询商品
     * @param serviceId
     * @param hot
     * @return
     */
    List<BdServiceProduct> findProductList(int serviceId, int hot);

}
