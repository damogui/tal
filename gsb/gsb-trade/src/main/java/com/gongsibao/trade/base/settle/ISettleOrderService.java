package com.gongsibao.trade.base.settle;

import com.gongsibao.entity.trade.settle.SettleOrder;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface ISettleOrderService extends IPersistableService<SettleOrder> {

    List<SettleOrder> bySettleId(Integer settleId);
}
