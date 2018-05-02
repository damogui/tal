package com.netsharp.rest.base.product;

import com.gongsibao.entity.cms.Product;
import com.gongsibao.entity.cms.ProductTemplate;

public interface IProService {
    /**
     * @Description:TODO 根据产品获取最新的显示且发布的cms信息
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 20:00
     */
    Product getLastCmsByProdId(Integer productId);
    /**
     * @Description:TODO 根据cmsid和区域id获取，该产品的产品详情
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 20:00
     */
    ProductTemplate getProductTemplateByCmsIdAndCityId(Integer cmsId, Integer cityId);
}
