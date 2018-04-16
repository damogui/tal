package com.gongsibao.rest.service.product.impl;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

import com.gongsibao.product.base.IPriceService;
import com.gongsibao.rest.common.web.BdCity;
import com.gongsibao.rest.service.product.IProductPriceService;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductPriceService implements IProductPriceService {
    IDictService bdDictService= ServiceFactory.create(IDictService.class);
    IPriceService priceService= ServiceFactory.create(IPriceService.class);
    IOrganizationService ucOrganizationService= ServiceFactory.create(IOrganizationService.class);
    @Override
    public List<BdCity> findProductCities(int productId, Collection<Integer> organizationIds) {
        List<Integer> allOrgIds = null;
        if (CollectionUtils.isNotEmpty(organizationIds)) {
            // 查询所有节点id
            allOrgIds = ucOrganizationService.getChildIdsByParentIds(organizationIds);
        }

        Set<Integer> cityIds = new HashSet<>();
        // 查产品所有定价地区id
        List<Integer> productCityIds = priceService.findCityIdsByProductIdAndOrganizationIds(productId, allOrgIds);

        // 2. 所有定价地区的下级ids
        List<Integer> childCityIds = bdDictService.findChildIds(productCityIds);

        // 1. 所有定价地区id的上级ids
        List<Integer> parentCityIds = bdDictService.findParentIds(productCityIds);

        cityIds.addAll(productCityIds);
        cityIds.addAll(childCityIds);
        cityIds.addAll(parentCityIds);

        List<BdCity> cities = BdCity.getCity(bdDictService.findByIds(new ArrayList<>(cityIds)));

        if (CollectionUtils.isNotEmpty(cities)) {
            cities.sort((o1, o2) -> {
                int po1 = NumberUtils.toInt(o1.getParent());
                int po2 = NumberUtils.toInt(o2.getParent());
                return po1 - po2;
            });
        }
        return cities;
    }

    @Override
    public List<Dict> findProductPropertyIds(int productId, int cityId) {
        List<Integer> propertyIds = priceService.findProductPropertyIds(productId, cityId);
        if (CollectionUtils.isEmpty(propertyIds)) {
            return new ArrayList<>();
        }

        return bdDictService.findByIds(propertyIds);
    }
}
