package com.gongsibao.u8.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayReceiptStatus;
import org.netsharp.core.annotations.Transaction;

public interface IPayService extends IPersistableService<Pay> {

    Boolean changeReceiptStatus(int payId, PayReceiptStatus receiptStatus);

    Pay getById(int payId);

    @Transaction
    int updateAuditStatus(int payId, int auditStatusId, int oldStatusId, int successStatusId);
}
