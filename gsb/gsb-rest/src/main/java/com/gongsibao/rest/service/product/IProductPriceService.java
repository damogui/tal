package com.gongsibao.rest.service.product;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.rest.common.web.BdCity;

import java.util.Collection;
import java.util.List;

public interface IProductPriceService {
    List<BdCity> findProductCities(int productId, Collection<Integer> organizationIds);
    List<Dict> findProductPropertyIds(int productId, int cityId);
}
