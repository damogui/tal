package com.gongsibao.bd.service.auditLog;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

import java.util.List;

/**
 * Created by win on 2018/3/24.
 */
/*订单业绩审核*/
public class OrderPerformanceAudit  extends AbstractAuditLogService{
    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        return null;
    }

    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.DdYjSq;
    }

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/performance";
    }
}
