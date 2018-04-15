package com.gongsibao.trade.service.action.audit.settle;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.entity.trade.settle.dict.SettleHandleStatus;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.IRefundService;
import com.gongsibao.trade.base.settle.ISettleService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.util.StringManager;

import java.util.HashMap;
import java.util.Map;

public class ActionAuditSettleWriteBack implements IAction{

	IAuditService auditService = ServiceFactory.create(IAuditService.class);
    ISettleService settleService = ServiceFactory.create(ISettleService.class);
	@Override
	public void execute(ActionContext ctx) {
		AuditContext auditContext = (AuditContext) ctx.getItem();
        AuditLog auditLog = (AuditLog) ctx.getStatus().get("auditLog");
        Settle settle = (Settle) ctx.getStatus().get("settle");

        audit(auditContext.getState(), auditLog, settle, auditContext.getremark());
	}

    @Transaction
    private void audit(AuditState state, AuditLog auditLog, Settle settle, String remark) {
        switch (state.getValue ()) {
            case 0://驳回审核
                auditService.auditRejected (auditLog.getId (), remark);
                if (auditLog.getLevel() == 1) {
                    settleService.updateStatusHandleStatus(settle.getId(), SettleHandleStatus.PLATFORM_REJECT);
                } else if (auditLog.getLevel() == 2) {
                    settleService.updateStatusHandleStatus(settle.getId(), SettleHandleStatus.FINANCIAL_REJECT);
                }
                break;
            case 1://通过审核
                auditService.auditApproved (auditLog.getId (), remark);
                //当最后级别审核通过时，修改合同实体审核状态为审核通过
                if (auditLog.getLevel() == 1) {
                    settleService.updateStatusHandleStatus(settle.getId(), SettleHandleStatus.FINANCIAL_AUDITING);
                } else if (auditLog.getLevel() == 2) {
                    settleService.updateStatusHandleStatus(settle.getId(), SettleHandleStatus.AUDIT_PASS);
                }
                break;
        }
    }

}
