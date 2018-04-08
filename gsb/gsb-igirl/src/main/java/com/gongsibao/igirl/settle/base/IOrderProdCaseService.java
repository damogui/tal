package com.gongsibao.igirl.settle.base;

import com.gongsibao.entity.igirl.settle.OrderProdCase;
import org.netsharp.base.IPersistableService;

import java.util.Collection;
import java.util.List;


public interface IOrderProdCaseService extends IPersistableService<OrderProdCase> {

    List<OrderProdCase> byIds(List<Integer> ids);

    List<OrderProdCase> byCaseId(Integer caseId);

    List<OrderProdCase> byOrderId(Integer orderId);

    boolean updateToSettle(Integer caseId);

}
