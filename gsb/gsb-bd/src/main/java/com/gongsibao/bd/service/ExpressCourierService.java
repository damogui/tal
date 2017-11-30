package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IExpressCourierService;
import com.gongsibao.entity.bd.ExpressCourier;

@Service
public class ExpressCourierService extends PersistableService<ExpressCourier> implements IExpressCourierService {

    public ExpressCourierService(){
        super();
        this.type=ExpressCourier.class;
    }
}