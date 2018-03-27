package com.gongsibao.trade.service.action.audit.payperformance;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Map;

public class ActionAuditPayPerWriteBack implements IAction {

    IAuditService auditService = ServiceFactory.create (IAuditService.class);

    INDepPayService depPayService = ServiceFactory.create (INDepPayService.class);

    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem ();

        Map<String, Object> objectMap = ctx.getStatus ();
        //本次审核通过或驳回
        AuditState state = auditContext.getState ();
        //审核意见
        String remark = auditContext.getremark ();
        AuditLog auditLog = (AuditLog) objectMap.get ("auditLog");
        NDepPay nDepPay = (NDepPay) objectMap.get ("nDepPay");
        //审核
        audit (state, auditLog, nDepPay, remark);

        //TODO:获取需要通知审核的审核人id

    }

    private void audit(AuditState state, AuditLog auditLog, NDepPay nDepPay, String remark) {
        switch (state.getValue ()) {
            case 0://驳回审核
                auditService.auditRejected (auditLog.getId (), remark);
                depPayService.updateStatus (nDepPay.getId (), AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved (auditLog.getId (),remark);
                if (auditLog.getLevel ().equals (auditLog.getMaxLevel ())) {
                    depPayService.updateStatus (nDepPay.getId (), AuditStatusType.Shtg);
                }
                break;
        }
    }
}
