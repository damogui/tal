package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.bd.AuditLog;

public interface IAuditService extends IPersistableService<AuditLog> {

    //审核通过
    @Transaction
    Boolean auditApproved(Integer auditId);

    //审核驳回
    @Transaction
    Boolean auditRejected(Integer auditId, String remark);
}
