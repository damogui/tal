package com.gongsibao.rest.service.product;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.rest.common.web.BdCity;
import com.gongsibao.rest.dto.product.ProductPriceDTO;

import java.util.Collection;
import java.util.List;

public interface IProductPriceService {
    List<BdCity> findProductCities(int productId, Collection<Integer> organizationIds);

    List<Dict> findProductPropertyIds(int productId, int cityId);


    /**
     * @param
     * @return
     * @Description:商品详情页 - 商品定价查询
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/16
     */
    List<ProductPriceDTO> productPriceList(Integer productId, Integer cityId, Integer propertyId);
}
