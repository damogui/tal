package com.gongsibao.trade.web;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;

import java.util.List;

/**
 * Created by win on 2018/3/23.
 */
public class AuditPayPerformanceListPart extends AdvancedListPart {

    @Override
    public List<?> doQuery(Oql oql) {
        StringBuilder selects = new StringBuilder ();
        selects.append ("AuditLog.*,");
        selects.append ("AuditLog.nDepPay.*,");
        selects.append ("AuditLog.nDepPay.order.*");

        IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);
        oql.setSelects (selects.toString ());
        List<AuditLog> auditLogs = auditLogService.queryList (oql);
        return auditLogs;
    }
}
