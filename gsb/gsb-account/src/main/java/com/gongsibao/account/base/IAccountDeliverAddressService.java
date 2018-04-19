package com.gongsibao.account.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.acount.AccountDeliverAddress;
import org.netsharp.core.annotations.Transaction;

import java.beans.Transient;
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

    /**
     * 判断是否存在
     *
     * @param accountDeliverAddress AccountDeliverAddress
     * @return
     */
    int exists(AccountDeliverAddress accountDeliverAddress);

    /**
     * 更新默认
     *
     * @param accountId 账号ID
     * @param pkid 住址ID
     */
    @Transaction
    void updateDefault(Integer accountId,Integer pkid);

    /**
     * 查询一条记录
     *
     * @param accountId 账号ID
     * @param pkid 住址ID
     * @return
     */
    AccountDeliverAddress byPidAccountId(Integer accountId,Integer pkid);

    /**
     * 删除收货地址
     *
     * @param pkid 主键
     */
    @Transaction
    void delete(Integer pkid);
}