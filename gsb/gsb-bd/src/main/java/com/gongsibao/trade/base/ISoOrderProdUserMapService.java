package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.OrderProdUserMap;
import org.netsharp.base.IPersistableService;

import java.util.List;
import java.util.Map;

public interface ISoOrderProdUserMapService extends IPersistableService<OrderProdUserMap> {

    Map<Integer, List<OrderProdUserMap>> findYWYByOrderProdIds(List<Integer> prodIds);
}
