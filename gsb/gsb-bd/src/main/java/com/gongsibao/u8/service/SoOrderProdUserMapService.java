package com.gongsibao.u8.service;

import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.u8.base.ISoOrderProdUserMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import java.util.List;
import java.util.Map;

@Service
public class SoOrderProdUserMapService extends PersistableService<OrderProdUserMap> implements ISoOrderProdUserMapService {
    @Override
    public Map<Integer, List<OrderProdUserMap>> findYWYByOrderProdIds(List<Integer> prodIds) {
        return null;
    }
}
