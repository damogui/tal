package com.gongsibao.trade.service.action.audit.refund;

import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepRefund;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepRefundService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;

/*退款消息*/
public class ActionAuditRefundSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Refund refund = (Refund) objectMap.get("refund");

        //此处没有用，查询浪费
//        Integer orderId = (Integer) objectMap.get("orderId");
//        SoOrder soOrder = AuditHelper.getOrderById(orderId);
        
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, refund, remark);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, Refund refund, String remark) {

        if (refund == null || auditLog == null) {

            return;
        }
        String orderNo = AuditHelper.getOrderNoById(refund.getOrderId());
        String telOwner=UserHelper.getEmployeTelById(refund.getCreatorId());
        switch (state.getValue()) {
            case 0://驳回审核
                if (refund.getCreatorId() != null) {

                    String content = String.format("【退款审核提醒】您好，您有1个订单提交的退款申请审核不通过，订单编号为【%s】，原因为【%s】,请知悉", orderNo, remark);
                    SmsHelper.send(telOwner, content);//订单业务员
                    sendAuditFail(UserHelper.getEmployeeName(refund.getCreatorId()), refund.getId(), orderNo, remark);//退款业绩相关业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【退款审核提醒】您好，您有1个订单提交的退款申请已审核通过，订单编号为【%s】，请知悉", orderNo);
                    SmsHelper.send(telOwner, content);//订单业务员
                    sendAuditPass(UserHelper.getEmployeeName(refund.getCreatorId()), refund.getId(), orderNo);//退款业绩相关业务员
                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, orderNo);//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item : userIds
                ) {
            String content = String.format("【退款待审核提醒】您好，有1个退款申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }
    INDepRefundService nDepRefundService = ServiceFactory.create(INDepRefundService.class);

    /*审核通过*/
    private void sendAuditPass(String owerName, Integer refundId, String no) {


        List<NDepRefund> ndeps = nDepRefundService.queryByRefundId(refundId);
        for (NDepRefund item : ndeps
                ) {
            String content = String.format("【退款业绩审核提醒】您好，【业务员】分配给您的退款业绩，审核已通过，订单编号为【%s】，请知悉", owerName, no);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }
    }

    /*审核失败*/
    private void sendAuditFail(String owerName, Integer refundId, String orderNo, String remark) {

        List<NDepRefund> ndeps = nDepRefundService.queryByRefundId(refundId);
        for (NDepRefund item : ndeps
                ) {
            String content = String.format("【退款业绩审核提醒】您好，【%s】分配给您的退款业绩，审核未通过，订单编号为【%s】，原因为【%s】,请知悉", owerName, orderNo, remark);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }


    }
}
