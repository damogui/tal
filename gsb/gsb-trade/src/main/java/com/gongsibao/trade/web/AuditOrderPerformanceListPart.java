package com.gongsibao.trade.web;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;

import java.sql.Types;
import java.util.List;

/**
 * Created by win on 2018/3/22.
 */
/*订单业绩审核*/
public class AuditOrderPerformanceListPart extends AdvancedListPart {

    @Override
    public List<?> doQuery(Oql oql) {
        StringBuilder selects = new StringBuilder ();
        selects.append ("auditLog.*,");
        selects.append ("auditLog.soOrder.*,");
        //selects.append ("auditLog.soOrder.depReceivable.*");

        IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);
        oql.setSelects (selects.toString ());
        List<AuditLog> auditLogs = auditLogService.queryList (oql);

        List<AuditLog> auditLogs2 = (List<AuditLog>) super.doQuery (oql);

        return auditLogs;
    }


}
