package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderProdOrganizationMap;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;

@Service
public class OrderProdOrganizationMapService extends PersistableService<OrderProdOrganizationMap> implements IOrderProdOrganizationMapService {

    public OrderProdOrganizationMapService(){
        super();
        this.type=OrderProdOrganizationMap.class;
    }
}