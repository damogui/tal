package com.gongsibao.igirl.base;

import com.gongsibao.entity.igirl.OrderProdCase;
import org.netsharp.base.IPersistableService;

import java.util.List;


public interface IOrderProdCaseService extends IPersistableService<OrderProdCase> {

    List<OrderProdCase> byCaseId(Integer caseId);
}
