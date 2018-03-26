package com.gongsibao.trade.service.action.audit.performance;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Map;

public class ActionAuditPerformanceWriteBack implements IAction{

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    INDepReceivableService nDepReceivableService = ServiceFactory.create(INDepReceivableService.class);

    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem();

        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        NDepReceivable nDepReceivable = (NDepReceivable) objectMap.get("nDepReceivable");
        //审核
        audit(state, auditLog, nDepReceivable, remark);

        //TODO:获取需要通知审核的审核人id

    }

    private void audit(AuditState state, AuditLog auditLog, NDepReceivable nDepReceivable, String remark) {
        switch (state.getValue()) {
            case 0://驳回审核
                auditService.auditRejected(auditLog.getId(), remark);
                nDepReceivableService.updateStatus(nDepReceivable.getId(), AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId());
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    nDepReceivableService.updateStatus(nDepReceivable.getId(), AuditStatusType.Shtg);
                }
                break;
        }
    }

}
