package com.gongsibao.trade.base;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.Pay;

import java.util.Collection;
import java.util.Date;

public interface IPayService extends IPersistableService<Pay> {

    @Transaction
    Boolean applyPay(Pay pay);

    @Transaction
    void updateStatus(Integer id, AuditStatusType bhsh);
    @Transaction
    Integer auditPass(String payTime, Integer formId);

    Boolean updatePayStatus(Integer payId, Integer successStatusId, Integer oldSuccessStatusId, Date confirmTime, String onlineTradeNo);

    int countByOrderIds(Collection<Integer> orderIds);
}