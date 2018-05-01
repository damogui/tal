package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.bd.service.auditLog.AuditOrderPerformanceService;
import com.gongsibao.entity.bd.AuditLog;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditRefundService;
import com.gongsibao.entity.trade.SoOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*创建订单业绩的审核*/
public class ActionApplyOrderPerformanceAudit implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        /*订单业绩开始审核*/
        SoOrder entity = (SoOrder) ctx.getItem ();
        AbstractAuditService service = AuditServiceFactory.create(AuditOrderPerformanceService.class);//进行重写
        List<AuditLog> audits=service.execute(entity.getId (), SessionManager.getUserId ());
        Map<String, Object> statusMap = new HashMap();
        statusMap.put ("audits", audits);
        ctx.setStatus (statusMap);
    }

}
