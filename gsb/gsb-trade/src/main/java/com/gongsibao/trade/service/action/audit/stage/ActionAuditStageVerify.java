package com.gongsibao.trade.service.action.audit.stage;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.u8.base.ISoOrderService;

public class ActionAuditStageVerify implements IAction {

    IAuditService auditService = ServiceFactory.create(IAuditService.class);
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        if (auditContext == null) {
            throw new BusinessException("审核信息不能为空");
        }
        AuditLog auditLog = auditService.getById(auditContext.getAuditLogId());
        if (auditLog == null) {
            throw new BusinessException("审核信息不能为空");
        }
        if (auditContext.getState().equals(AuditState.NOTPASS) && StringManager.isNullOrEmpty(auditContext.getremark())) {
            throw new BusinessException("当审核失败时，审批意见不能为空");
        }

        if (!auditLog.getStatus().equals(AuditLogStatusType.TOAUDIT)) {
            throw new BusinessException("该审核状态不是【" + AuditLogStatusType.TOAUDIT.getText() + "】,禁止审核");
        }

        if (auditLog.getType().equals(AuditLogType.Fqsq.getText())) {
            throw new BusinessException("该审核类别不是【" + AuditLogType.Fqsq.getText() + "】,禁止审核");
        }

        SoOrder order = orderService.getByOrderId(auditLog.getFormId());
        if (order == null) {
            throw new BusinessException("该分期信息不存在");
        }
        Map<String, Object> statusMap = new HashMap<String, Object>();
        statusMap.put("auditLog", auditLog);
        statusMap.put("soOrder", order);
        ctx.setStatus(statusMap);
    }

}
