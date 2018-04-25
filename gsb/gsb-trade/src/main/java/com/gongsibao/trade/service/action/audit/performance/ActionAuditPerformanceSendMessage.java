package com.gongsibao.trade.service.action.audit.performance;

import com.gongsibao.bd.service.auditLog.*;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.List;
import java.util.Map;

/*订单业绩发送消息*/
public class ActionAuditPerformanceSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        SoOrder soOrder = (SoOrder) objectMap.get("soOrder");
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, soOrder, remark);


    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark) {

        if (soOrder == null || auditLog == null) {

            return;
        }

        AbstractAuditLogService service = AuditFactory.getAudit(OrderPerformanceAudit.class);//获取要通知的电话
        List<String> tels = service.getAuditPassTel();

        for (String tel:
        tels) {
          if (!StringManager.isNullOrEmpty(tel)){
              String content = String.format("【订单业绩待审核提醒】您好，【业务员%s】提交1个订单业绩申请待您审核，订单编号为【%s】，请及时审核", soOrder.getOwner()==null?"":soOrder.getOwner().getName(), soOrder.getNo());
              SmsHelper.send(tel, content);//电话和内容
          }

        }

    }

}
