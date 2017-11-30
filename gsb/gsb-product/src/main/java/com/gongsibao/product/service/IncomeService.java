package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Income;
import com.gongsibao.product.base.IIncomeService;

@Service
public class IncomeService extends PersistableService<Income> implements IIncomeService {

    public IncomeService(){
        super();
        this.type=Income.class;
    }
}