package com.gongsibao.gardian.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.acount.AccountDeliverAddress;
import com.gongsibao.gardian.base.IAccountDeliverAddressService;

@Service
public class AccountDeliverAddressService extends PersistableService<AccountDeliverAddress> implements IAccountDeliverAddressService {

    public AccountDeliverAddressService(){
        super();
        this.type=AccountDeliverAddress.class;
    }
}