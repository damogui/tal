package com.gongsibao.account.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.acount.AccountDeliverAddress;

import java.util.List;

public interface IAccountDeliverAddressService extends IPersistableService<AccountDeliverAddress> {

    /**
     * 查询当前用户的第一个默认收货地址
     *
     * @param accountId 账号ID
     * @return AccountDeliverAddress
     */
    AccountDeliverAddress queryDefaultFirst(Integer accountId);

    /**
     * 根据当前用户获取收货地址列表
     *
     * @param accountId 账号ID
     * @return
     */
    List<AccountDeliverAddress> queryList(Integer accountId);
}