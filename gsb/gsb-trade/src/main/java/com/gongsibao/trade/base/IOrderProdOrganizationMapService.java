package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdOrganizationMap;

import java.util.List;

public interface IOrderProdOrganizationMapService extends IPersistableService<OrderProdOrganizationMap> {

    public Boolean updateOrganizationMap(Integer orderProdId, Integer supplierId);

    List<OrderProdOrganizationMap> getListByOrderNo(String orderNo);

}