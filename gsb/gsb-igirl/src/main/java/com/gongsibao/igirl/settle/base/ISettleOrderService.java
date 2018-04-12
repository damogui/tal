package com.gongsibao.igirl.settle.base;

import com.gongsibao.entity.igirl.settle.SettleOrder;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface ISettleOrderService extends IPersistableService<SettleOrder> {

    List<SettleOrder> bySettleId(Integer settleId);
}
