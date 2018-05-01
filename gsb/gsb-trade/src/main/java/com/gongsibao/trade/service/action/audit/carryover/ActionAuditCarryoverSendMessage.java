package com.gongsibao.trade.service.action.audit.carryover;

import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;

/*结转审核*/
public class ActionAuditCarryoverSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        SoOrder soOrder = (SoOrder) objectMap.get("soOrder");//来源订单
        Integer toOrderId = (Integer) objectMap.get("toOrderId");//去向订单
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, soOrder, remark, toOrderId);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark, int toOrderId) {

        if (soOrder == null || auditLog == null) {

            return;
        }
        switch (state.getValue()) {
            case 0://驳回审核
                if (soOrder.getOwner() != null) {

                    String content = String.format("【结转审核提醒】您好，您有1个订单提交的结转申请审核不通过，订单编号为【%S】，原因为【%s】,请知悉", soOrder.getNo(), remark);
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                    sendAuditFail(soOrder.getOwner().getName(), remark, toOrderId);//去向订单
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【结转审核提醒】您好，您有1个订单提交的结转申请已审核通过，订单编号为【%S】，请知悉", soOrder.getNo());
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//来源订单业务员
                    sendAuditPass(soOrder.getOwner().getName(), toOrderId);//去向业务员
                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getFormId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, soOrder.getNo());//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item : userIds
                ) {
            String content = String.format("【结转待审核提醒】您好，有1个结转申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }

    /*审核通过*/
    private void sendAuditPass(String owerName, Integer toOrderId) {

        SoOrder order = AuditHelper.getOrderById(toOrderId);//去向订单
        if (order==null){return;}
        String content = String.format("【结转审核提醒】您好，【%s】结转给您的订单编号为【%S】的申请，审核已通过，请知悉", owerName, order.getNo());
        SmsHelper.send(UserHelper.getEmployeTelById(order.getOwnerId()), content);//电话和内容
    }

    /*审核失败*/
    private void sendAuditFail(String owerName, String remark, int toOrderId) {

        SoOrder order = AuditHelper.getOrderById(toOrderId);//去向订单
        if (order==null){return;}
        String content = String.format("【结转审核提醒】您好，【%S】结转给您的订单编号为【%S】的申请，审核未通过，原因为【%S】,请知悉", owerName, order.getNo(), remark);
        SmsHelper.send(UserHelper.getEmployeTelById(order.getOwnerId()), content);//电话和内容


    }

}
