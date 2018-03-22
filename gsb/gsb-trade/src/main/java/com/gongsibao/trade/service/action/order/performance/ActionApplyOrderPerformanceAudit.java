package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.SingleBackAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.persistence.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

/*创建订单业绩的审核*/
public class ActionApplyOrderPerformanceAudit implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        /*订单业绩开始审核*/
        SoOrder entity = (SoOrder) ctx.getItem ();
        AbstractAuditLogService service = AuditFactory.getAudit(SingleBackAudit.class);
        service.execute(AuditLogType.DdYjSq, entity.getId (), SessionManager.getUserId ());
    }

}
