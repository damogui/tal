package com.gongsibao.trade.web.settle;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.settle.ISettleService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import java.util.List;

public class SettleFormPart extends FormPart {
    ISettleService settleService = ServiceFactory.create(ISettleService.class);

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    public Settle settleDetail(Integer id) {
        if (null == id || id == 0) {
            return null;
        }

        return settleService.byId(id);
    }

    public List<AuditLog> getLogs(Integer id) {
        if (null == id || id == 0) {
            return null;
        }

        return auditService.getByTypeIdFormId(AuditLogType.Jssh, id);
    }


}
