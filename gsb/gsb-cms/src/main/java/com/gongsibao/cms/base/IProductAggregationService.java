package com.gongsibao.cms.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cms.ProductAggregation;

import java.util.List;

public interface IProductAggregationService extends IPersistableService<ProductAggregation> {
    /**
     * @Description:TODO 根据cmsid获取该cms下所有的聚合信息
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/11 20:11
     */
    List<ProductAggregation> getByCmsProdId(Integer cmsprodid);

    List<ProductAggregation> getByCmsParentId(Integer id);
}