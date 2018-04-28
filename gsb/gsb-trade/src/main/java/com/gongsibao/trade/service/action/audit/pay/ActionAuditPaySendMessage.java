package com.gongsibao.trade.service.action.audit.pay;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.List;
import java.util.Map;

public class ActionAuditPaySendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Pay pay = (Pay) objectMap.get("pay");
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, pay, remark);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, Pay pay, String remark) {

        if (pay == null || auditLog == null) {

            return;
        }
        switch (state.getValue()) {
            case 0://驳回审核
                if (pay.getOrderNo() != null) {

                    String content = String.format("【回款审核提醒】您好，您有1个订单提交的回款申请审核不通过，订单编号为【%s】，原因为【%s】,请知悉", pay.getOrderNo(), remark);
                    SmsHelper.send(UserHelper.getEmployeTelById(pay.getCreatorId()), content);//订单业务员
                    sendAuditFail(UserHelper.getEmployeeName(pay.getCreatorId()), pay.getId(), pay.getOrderNo(), remark);//业绩相关业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【回款审核提醒】您好，您有1个订单提交的回款申请已审核通过，订单编号为【%s】，请知悉",  pay.getOrderNo());
                    SmsHelper.send(UserHelper.getEmployeTelById(pay.getCreatorId()), content);//订单业务员
                    sendAuditPass(UserHelper.getEmployeeName(pay.getCreatorId()),  pay.getId(),  pay.getOrderNo());//业绩相关业务员
                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, pay.getOrderNo());//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item : userIds
                ) {
            String content = String.format("【回款待审核提醒】您好，有1个回款申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }

    IOrderPayMapService  orderPayMapService = ServiceFactory.create(IOrderPayMapService.class);

    /*审核通过*/
    private void sendAuditPass(String owerName, Integer payId, String no) {


        List<OrderPayMap> orderPayMaps = orderPayMapService.queryByPayId(payId);
        for (OrderPayMap item : orderPayMaps
                ) {
            String content = String.format("【回款审核提醒】您好，【%s】分配给您的订单编号为【%s】的回款，审核已通过，请知悉", owerName, no);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getCreatorId()), content);//电话和内容

        }
    }

    /*审核失败*/
    /*支付id获取相关的订单的业务员然后发送短信*/
    private void sendAuditFail(String owerName, Integer payId, String orderNo, String remark) {

        List<OrderPayMap> orderPayMaps = orderPayMapService.queryByPayId(payId);
        for (OrderPayMap item : orderPayMaps
                ) {
            String content = String.format("【回款审核提醒】您好，【%s】分配给您的订单编号为【%s】的回款，审核未通过，原因为【%s】，请知悉", owerName, orderNo, remark);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getCreatorId()), content);//电话和内容

        }


    }

}
