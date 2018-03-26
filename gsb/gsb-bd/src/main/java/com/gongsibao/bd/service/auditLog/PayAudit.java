package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

/*回款*/
public class PayAudit extends AbstractAuditLogService {

    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {

        return null;
    }

    @Override
    protected AuditLogType setAuditLogType() {

        return AuditLogType.Sksq;
    }

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/pay";
    }
}
