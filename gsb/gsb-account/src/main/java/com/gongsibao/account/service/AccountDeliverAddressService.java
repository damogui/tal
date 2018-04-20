package com.gongsibao.account.service;

import com.gongsibao.entity.igirl.ic.ex.dict.BooleanType;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
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

    @Override
    public void updateDefault(Integer accountId, Integer pkid) {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.add("account_id",accountId,Types.INTEGER);
        String sql = String.format("update uc_account_deliver_address address set address.is_default = if(pkid=%s,1,0) where account_id = ? ",pkid);
        this.pm.executeNonQuery(sql,queryParameters);
    }

    @Override
    public AccountDeliverAddress byPidAccountId(Integer accountId, Integer pkid) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("AccountDeliverAddress.*");
        oql.setFilter(" accountId = ? and id = ? ");
        oql.getParameters().add("accountId",accountId,Types.INTEGER);
        oql.getParameters().add("id",pkid,Types.INTEGER);
        return this.pm.queryFirst(oql);
    }

    @Override
    public void delete(Integer pkid) {
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.add("pkid",pkid,Types.INTEGER);
        this.pm.executeNonQuery("delete from uc_account_deliver_address where pkid = ? ",queryParameters);
    }
}