package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.IncomeCity;
import com.gongsibao.product.base.IIncomeCityService;

@Service
public class IncomeCityService extends PersistableService<IncomeCity> implements IIncomeCityService {

    public IncomeCityService(){
        super();
        this.type=IncomeCity.class;
    }
}