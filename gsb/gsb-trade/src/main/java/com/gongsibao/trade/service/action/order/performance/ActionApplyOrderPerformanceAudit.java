package com.gongsibao.trade.service.action.order.performance;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.SingleBackAudit;
import com.gongsibao.entity.trade.SoOrder;

/*创建订单业绩的审核*/
public class ActionApplyOrderPerformanceAudit implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        /*订单业绩开始审核*/
        SoOrder entity = (SoOrder) ctx.getItem ();
        AbstractAuditLogService service = AuditFactory.getAudit(SingleBackAudit.class);
        service.execute(entity.getId (), SessionManager.getUserId ());
    }

}
