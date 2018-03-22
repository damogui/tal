package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.ContractAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractAudit implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
        Integer userId = SessionManager.getUserId();
        Map<String, Object> status = ctx.getStatus();
        //合同审核
        AbstractAuditLogService auditLogService = AuditFactory.getAudit(ContractAudit.class);
        List<AuditLog> auditLogList = auditLogService.execute(AuditLogType.Htsq, contract.getId(), userId);
        //审核记录
        List<Integer> audiUserIdList = new ArrayList<>();
        for (AuditLog auditLog : auditLogList) {
            //去掉自己
            if (!auditLog.getCreatorId().equals(userId)) {
                audiUserIdList.add(auditLog.getCreatorId());
            }
        }
        //需要发通知的人员id
        status.put("audiUserIdList",audiUserIdList);


    }
}
