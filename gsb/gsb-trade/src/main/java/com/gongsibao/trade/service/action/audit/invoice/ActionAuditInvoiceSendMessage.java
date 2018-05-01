package com.gongsibao.trade.service.action.audit.invoice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;

/*发票*/
public class ActionAuditInvoiceSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        Invoice invoice = (Invoice) objectMap.get("invoice");
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        auditSend(state, auditLog, invoice.getId(), remark);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, Integer invoiceId, String remark) {

        if (invoiceId == 0 || auditLog == null) {

            return;
        }
        String content = "";
        List<String> listTels = new ArrayList<>();
        switch (state.getValue()) {
            case 0://驳回审核
                content = String.format("【发票审核提醒】您好，您有1个订单提交的发票申请审核不通过，订单编号为【%s】，原因为【%s】,请知悉", AuditHelper.getOrderNosByInvoiceId(invoiceId), remark);
                listTels = AuditHelper.getOwnerTelsByInvoiceId(invoiceId);//按一对多处理
                for (String tel : listTels
                        ) {
                    SmsHelper.send(tel, content);//订单业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    content = String.format("【发票审核提醒】您好，您有1个订单提交的发票申请已审核通过，订单编号为【%s】，请知悉", AuditHelper.getOrderNosByInvoiceId(invoiceId));
                    listTels = AuditHelper.getOwnerTelsByInvoiceId(invoiceId);//按一对多处理
                    for (String tel : listTels
                            ) {
                        SmsHelper.send(tel, content);//订单业务员
                    }

                } else {
                    //通知下一级
                    List<Integer> userIds = AuditHelper.getNextLevelUserIds(auditLog.getFormId(), auditLog.getType().getValue(), auditLog.getLevel() + 1);//获取下一级要通知的人 fromId
                    sendNextAudit(userIds, AuditHelper.getOrderNosByInvoiceId(invoiceId));//下一级审核

                }
                break;
        }

    }

    /*下一级审核*/
    private void sendNextAudit(List<Integer> userIds, String no) {
        for (Integer item : userIds
                ) {
            String content = String.format("【发票待审核提醒】您好，有N个发票申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }
}
