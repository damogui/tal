package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;

@Service
public class PriceService extends PersistableService<Price> implements IPriceService {

    public PriceService(){
        super();
        this.type=Price.class;
    }
}