package com.gongsibao.account.service;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.account.base.IAccountDeliverAddressService;
import com.gongsibao.entity.acount.AccountDeliverAddress;

import java.sql.Types;
import java.util.List;

@Service
public class AccountDeliverAddressService extends PersistableService<AccountDeliverAddress> implements IAccountDeliverAddressService {

    public AccountDeliverAddressService(){
        super();
        this.type=AccountDeliverAddress.class;
    }

    @Override
    public AccountDeliverAddress queryDefaultFirst(Integer accountId) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("AccountDeliverAddress.*,city.{name}");
        oql.setFilter(" accountId = ? and defaulted = 1 ");
        oql.setOrderby(" defaulted desc, pkid desc ");
        oql.getParameters().add("accountId",accountId, Types.INTEGER);
        return this.pm.queryFirst(oql);
    }

    @Override
    public List<AccountDeliverAddress> queryList(Integer accountId) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("AccountDeliverAddress.*,city.{name}");
        oql.setFilter(" accountId = ? ");
        oql.setOrderby(" defaulted desc, pkid desc ");
        oql.getParameters().add("accountId",accountId, Types.INTEGER);
        return this.pm.queryList(oql);
    }

    @Override
    public int exists(AccountDeliverAddress accountDeliverAddress) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("AccountDeliverAddress.*");
        oql.setFilter(" accountId = ? and contacts = ? and mobilePhone = ? and cityId = ? and address = ? ");
        oql.getParameters().add("accountId",accountDeliverAddress.getAccountId(),Types.INTEGER);
        oql.getParameters().add("contacts",accountDeliverAddress.getContacts(),Types.VARCHAR);
        oql.getParameters().add("mobilePhone",accountDeliverAddress.getMobilePhone(),Types.VARCHAR);
        oql.getParameters().add("cityId",accountDeliverAddress.getCityId(),Types.INTEGER);
        oql.getParameters().add("address",accountDeliverAddress.getAddress(),Types.VARCHAR);
        return this.pm.queryCount(oql);
    }
}