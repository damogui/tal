package com.gongsibao.igirl.service;

import com.gongsibao.entity.igirl.OrderProdCase;
import com.gongsibao.igirl.base.IOrderProdCaseService;
import org.netsharp.service.PersistableService;

public class OrderProdCaseService extends PersistableService<OrderProdCase> implements IOrderProdCaseService {

    public OrderProdCaseService() {
        super();
        this.type = OrderProdCase.class;
    }

}
