package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.NU8BankSoPayMap;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.INU8BankSoPayMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by win on 2018/3/14.
 */
@Service
public class NU8BankSoPayMapService extends PersistableService<NU8BankSoPayMap> implements INU8BankSoPayMapService {

    public NU8BankSoPayMapService(){
        super();
        this.type=NU8BankSoPayMap.class;
    }
}
