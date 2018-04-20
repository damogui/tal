package com.gongsibao.trade.service.action.audit.pay;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.*;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.util.StringManager;

import java.util.Map;

public class ActionAuditPayWriteBack<T> implements IAction {

    IAuditService auditService = ServiceFactory.create(IAuditService.class);

    IPayService payService = ServiceFactory.create(IPayService.class);
    IOrderPayMapService orderPayMapService = ServiceFactory.create(IOrderPayMapService.class);//回写使用

    @Override
    public void execute(ActionContext ctx) {

        AuditContext auditContext = (AuditContext) ctx.getItem();

        Map<String, Object> objectMap = ctx.getStatus();
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核意见
        String remark = auditContext.getremark();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Pay pay = (Pay) objectMap.get("pay");
        Object obj = auditContext.getOtherInfo();
        if (obj == null) {
            //审核
            audit(state, auditLog, pay, remark,"");

        } else {
            //审核
            audit(state, auditLog, pay, remark,(String)obj);

        }


        //TODO:获取需要通知审核的审核人id

    }


    private void audit(AuditState state, AuditLog auditLog, Pay pay, String remark,String payTime) {
        switch (state.getValue()) {
            case 0://驳回审核
                auditService.auditRejected(auditLog.getId(), remark);

                payService.updateStatus(pay.getId(), AuditStatusType.Bhsh);
                break;
            case 1://通过审核
                auditService.auditApproved(auditLog.getId(), remark);
                //当最后级别审核通过时，修改合同实体审核状态为审核通过
                if (!StringManager.isNullOrEmpty(payTime)){
                    Integer execNum = payService.auditPass(payTime, auditLog.getFormId());//根据确认时间和支付时间更新
                }

                break;
        }
    }

}
