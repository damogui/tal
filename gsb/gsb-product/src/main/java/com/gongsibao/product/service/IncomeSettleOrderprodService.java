package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.IncomeSettleOrderprod;
import com.gongsibao.product.base.IIncomeSettleOrderprodService;

@Service
public class IncomeSettleOrderprodService extends PersistableService<IncomeSettleOrderprod> implements IIncomeSettleOrderprodService {

    public IncomeSettleOrderprodService(){
        super();
        this.type=IncomeSettleOrderprod.class;
    }
}