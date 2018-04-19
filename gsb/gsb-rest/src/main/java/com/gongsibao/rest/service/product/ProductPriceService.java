package com.gongsibao.rest.service.product;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;
import com.gongsibao.rest.web.common.web.BdCity;
import com.gongsibao.rest.web.dto.product.ProdPriceDTO;
import com.gongsibao.rest.base.product.IProductPriceService;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductPriceService implements IProductPriceService {
    IDictService dictService = ServiceFactory.create(IDictService.class);
    IPriceService priceService = ServiceFactory.create(IPriceService.class);
    IOrganizationService ucOrganizationService = ServiceFactory.create(IOrganizationService.class);

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
        List<Integer> childCityIds = dictService.findChildIds(productCityIds);

        // 1. 所有定价地区id的上级ids
        List<Integer> parentCityIds = dictService.findParentIds(productCityIds);

        cityIds.addAll(productCityIds);
        cityIds.addAll(childCityIds);
        cityIds.addAll(parentCityIds);

        List<BdCity> cities = BdCity.getCity(dictService.findByIds(new ArrayList<>(cityIds)));

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

        return dictService.findByIds(propertyIds);
    }

    @Override
    public List<ProdPriceDTO> productPriceList(Integer productId, Integer cityId, Integer propertyId) {
        List<ProdPriceDTO> dtoList = new ArrayList<>();
        // 查询产品 + 城市 + 特性的定价信息
        List<Price> prices = priceService.productServicePrice(productId, cityId, propertyId);
        if (prices.isEmpty()) {
            return dtoList;
        }

        Set<Integer> organizationIds = new LinkedHashSet<>();
        List<Integer> dictIds = new ArrayList<>();
        for (Price price : prices) {
            organizationIds.add(price.getPriceAudit().getOrganizationId());
            dictIds.add(price.getService().getUnitId());
            dictIds.add(price.getService().getTypeId());
        }

        // 批量取字典信息，下面封装使用
        Map<Integer, Dict> dictMap = dictService.findMapByIds(dictIds);

        // 默认取组织机构汉唐的
        int organizationId = 4;
        if (!organizationIds.contains(organizationId)) {
            organizationId = organizationIds.iterator().next(); // 取第一个
        }

        // 封装DTO对象
        for (Price price : prices) {
            Integer orgId = price.getPriceAudit().getOrganizationId();
            if (orgId.compareTo(organizationId) != 0) {
                continue;
            }

            Integer unitId = price.getService().getUnitId();
            Integer typeId = price.getService().getTypeId();

            ProdPriceDTO dto = new ProdPriceDTO();
            {
                dto.setPriceId(price.getId());
                dto.setIsMust(price.getNecessary() ? 1 : 0);
                dto.setPrice(price.getPrice());

                dto.setOrganizationId(orgId);

                dto.setUnitId(unitId);
                dto.setTypeId(typeId);
                Dict dict = dictMap.get(unitId);
                if (null != dict) {
                    dto.setUnitName(dict.getName());
                }
                dict = dictMap.get(typeId);
                if (null != dict) {
                    dto.setTypeName(dict.getName());
                }
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}
