package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdOrganizationMap;

import java.util.List;
import java.util.Map;

public interface IOrderProdOrganizationMapService extends IPersistableService<OrderProdOrganizationMap> {

    public Boolean updateOrganizationMap(Integer orderProdId, Integer supplierId);

    public List<OrderProdOrganizationMap> getListByOrderProdId(Integer orderProdId);

    List<OrderProdOrganizationMap> getListByOrderNo(String orderNo, int startIndex, int pageSize);

    Integer getCountByOrderNo(String orderNo);

    //根据明细订单id获取操作组名称
    Map<Integer, String> getOrderOperationGroup(List<Integer> orderProdIdList);

    List<OrderProdOrganizationMap> getOrderProdIdList(List<Integer> orderProdIdList);


}