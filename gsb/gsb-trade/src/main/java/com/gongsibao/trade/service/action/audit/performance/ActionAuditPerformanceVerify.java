package com.gongsibao.trade.service.action.audit.performance;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IOrderService;

public class ActionAuditPerformanceVerify implements IAction {

	IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);

    IOrderService orderService = ServiceFactory.create (IOrderService.class);//主实体是订单
    //INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);


    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem ();

        if (auditContext == null) {
            throw new BusinessException ("审核信息不能为空");
        }

        AuditLog auditLog = auditService.getById (auditContext.getAuditLogId ());

        if (auditLog == null) {
            throw new BusinessException ("审核信息不能为空");
        }

        if (auditContext.getState ().equals (AuditState.NOTPASS) && StringManager.isNullOrEmpty (auditContext.getremark ())) {
            throw new BusinessException ("当审核失败时，审批意见不能为空");
        }

        if (!auditLog.getStatus ().equals (AuditLogStatusType.TOAUDIT)) {
            throw new BusinessException ("该审核状态不是【" + AuditLogStatusType.TOAUDIT.getText () + "】,禁止审核");
        }

        if (!auditLog.getType ().equals (AuditLogType.DdYjSq)) {
            throw new BusinessException ("该审核类别不是【" + AuditLogType.DdYjSq.getText () + "】,禁止审核");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("SoOrder.*,");
        sb.append("SoOrder.owner");

        SoOrder soOrder = orderService.getSoOrderById (auditLog.getFormId (),sb.toString());//订单
        if (soOrder == null) {
            throw new BusinessException ("该的订单业绩信息不存在");
        }

        if (!soOrder.getDepReceivableAuditStatusId ().equals (AuditStatusType.Dsh)) {
            throw new BusinessException ("该订单业绩不是【" + AuditStatusType.Dsh.getText () + "】,禁止审核");
        }
        Map<String, Object> statusMap = new HashMap <String, Object>();
        statusMap.put ("auditLog", auditLog);
        statusMap.put ("soOrder", soOrder);
        ctx.setStatus (statusMap);
    }

}
