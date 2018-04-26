package com.gongsibao.trade.service.action.audit.performance;

import com.gongsibao.bd.service.auditLog.*;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.sql.Types;
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
        switch (state.getValue()) {
            case 0://驳回审核
                if (soOrder.getOwner() != null) {

                    String content = String.format("【订单业绩审核提醒】您好，您有1个订单提交的订单业绩申请审核不通过，订单编号为【%s】，原因为【%S】,请知悉", soOrder.getNo(),remark);
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                    sendAuditFail(soOrder.getOwner().getName(),soOrder.getId(), soOrder.getNo(), remark);//业绩相关业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【订单业绩审核提醒】您好，您有1个订单提交的订单业绩申请已审核通过，订单编号为【%s】，请知悉", soOrder.getNo());
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                    sendAuditPass(soOrder.getOwner().getName(),soOrder.getId(), soOrder.getNo());//业绩相关业务员
                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(soOrder.getId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, soOrder.getNo());//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item:userIds
             ) {
            String content = String.format("【订单业绩待审核提醒】您好，有1个订单业绩申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }

    /*审核通过*/
    private void sendAuditPass(String owerName,Integer id, String no) {


        List<NDepReceivable> ndeps = ndepService.getNDepsByOrderId(id);
        for (NDepReceivable item : ndeps
                ) {
            String content = String.format("【订单业绩审核提醒】您好，【%s】分配给您的订单业绩，审核已通过，订单编号为【%S】，请知悉",owerName, no);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }
    }

    /*审核失败*/
    INDepReceivableService ndepService = ServiceFactory.create(INDepReceivableService.class);

    private void sendAuditFail(String owerName,Integer id, String orderNo, String remark) {

        List<NDepReceivable> ndeps = ndepService.getNDepsByOrderId(id);
        for (NDepReceivable item : ndeps
                ) {
            String content = String.format("【订单业绩审核提醒】您好，【%S】分配给您的订单业绩，审核未通过，订单编号为【%S】，原因为【%S】,请知悉", owerName,orderNo, remark);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }


    }

}
