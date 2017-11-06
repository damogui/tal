package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.IncomeSettle;
import com.gongsibao.product.base.IIncomeSettleService;

@Service
public class IncomeSettleService extends PersistableService<IncomeSettle> implements IIncomeSettleService {

    public IncomeSettleService(){
        super();
        this.type=IncomeSettle.class;
    }
}