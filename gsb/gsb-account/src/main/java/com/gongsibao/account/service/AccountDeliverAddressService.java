package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDeliverAddressService;
import com.gongsibao.entity.acount.AccountDeliverAddress;

@Service
public class AccountDeliverAddressService extends PersistableService<AccountDeliverAddress> implements IAccountDeliverAddressService {

    public AccountDeliverAddressService(){
        super();
        this.type=AccountDeliverAddress.class;
    }
}