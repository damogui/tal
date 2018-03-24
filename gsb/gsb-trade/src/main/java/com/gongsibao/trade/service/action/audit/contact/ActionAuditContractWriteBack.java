package com.gongsibao.trade.service.action.audit.contact;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.service.action.audit.AuditContext;
import com.gongsibao.trade.service.action.audit.AuditState;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Map;

public class ActionAuditContractWriteBack implements IAction {

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem();

        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Contract contract = (Contract) objectMap.get("contract");
        //当审核通过时
        if (state.equals(AuditState.PASS)) {

        }
        //当审核驳回时
        if (state.equals(AuditState.NOTPASS)) {

        }


    }

}
