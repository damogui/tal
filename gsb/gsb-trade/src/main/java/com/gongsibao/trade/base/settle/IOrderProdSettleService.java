package com.gongsibao.trade.base.settle;

import com.gongsibao.entity.trade.settle.OrderProdSettle;
import org.netsharp.base.IPersistableService;

import java.util.List;


public interface IOrderProdSettleService extends IPersistableService<OrderProdSettle> {

    List<OrderProdSettle> byIds(List<Integer> ids);

    List<OrderProdSettle> byCaseId(Integer caseId);

    List<OrderProdSettle> byOrderId(Integer orderId);

    boolean updateToSettle(Integer caseId);

    boolean deleteByCaseId(Integer caseId);

}
