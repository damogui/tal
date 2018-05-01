package com.gongsibao.trade.service.action.audit.invoice;

import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IInvoiceService;

public class ActionAuditInvoiceWriteBack implements IAction {

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);

    IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Invoice invoice = (Invoice) objectMap.get("invoice");
        //审核
        audit(state, auditLog, invoice, remark);

        //TODO:获取需要通知审核的审核人id

    }

    private void audit(AuditState state, AuditLog auditLog, Invoice invoice, String remark) {
        switch (state.getValue()) {
            case 0://驳回审核
                auditService.auditRejected(auditLog.getId(), remark);
                invoiceService.updateStatus(invoice.getId(), AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId(),remark);
                //当最后级别审核通过时，修改合同实体审核状态为审核通过
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    invoiceService.updateStatus(invoice.getId(), AuditStatusType.Shtg);
                }
                break;
        }
    }

}
