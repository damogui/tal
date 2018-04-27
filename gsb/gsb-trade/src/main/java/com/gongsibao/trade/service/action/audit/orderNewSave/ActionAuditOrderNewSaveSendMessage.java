package com.gongsibao.trade.service.action.audit.orderNewSave;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.List;
import java.util.Map;

/*订单改价审核*/
public class ActionAuditOrderNewSaveSendMessage implements IAction {

    private IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    @Override
    public void execute(ActionContext ctx) {
        AuditContext auditContext = (AuditContext) ctx.getItem();
        Map<String, Object> objectMap = ctx.getStatus();
        AuditLog auditLog = (AuditLog) objectMap.get("auditLog");
        SoOrder soOrder = (SoOrder) objectMap.get("soOrder");
        //本次审核通过或驳回
        AuditState state = auditContext.getState();
        //审核
        //审核意见
        String remark = auditContext.getremark();
        //auditSend(state, auditLog, soOrder, remark);
        //推送icompnay公众号消息
        sendWxMsg(state, auditLog, soOrder);
    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark) {

        if (soOrder == null || auditLog == null) {

            return;
        }
        switch (state.getValue()) {
            case 0://驳回审核
                if (soOrder.getOwner() != null) {

                    String content = String.format("【回款业绩审核提醒】您好，您有1个订单提交的回款业绩申请审核不通过，订单编号为【%S】，原因为【%s】,请知悉", soOrder.getNo(), remark);
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                    sendAuditFail(soOrder.getOwner().getName(), soOrder.getId(), soOrder.getNo(), remark);//业绩相关业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【回款业绩审核提醒】您好，您有1个订单提交的回款业绩申请已审核通过，订单编号为【%s】，请知悉", soOrder.getNo());
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                    sendAuditPass(soOrder.getOwner().getName(), soOrder.getId(), soOrder.getNo());//业绩相关业务员
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
        for (Integer item : userIds
                ) {
            String content = String.format("【订单业绩待审核提醒】您好，有1个订单业绩申请待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
        }

    }

    /*审核通过*/
    private void sendAuditPass(String owerName, Integer id, String no) {


        List<NDepReceivable> ndeps = ndepService.getNDepsByOrderId(id);
        for (NDepReceivable item : ndeps
                ) {
            String content = String.format("【回款业绩审核提醒】您好，【%s】分配给您的回款业绩，审核已通过，订单编号为【%s】，请知悉", owerName, no);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }
    }

    /*审核失败*/
    INDepReceivableService ndepService = ServiceFactory.create(INDepReceivableService.class);

    private void sendAuditFail(String owerName, Integer id, String orderNo, String remark) {

        List<NDepReceivable> ndeps = ndepService.getNDepsByOrderId(id);
        for (NDepReceivable item : ndeps
                ) {
            String content = String.format("【回款业绩审核提醒】您好，【%s】分配给您的回款业绩，审核未通过，订单编号为【%s】，原因为【%s】,请知悉", owerName, orderNo, remark);
            SmsHelper.send(UserHelper.getEmployeTelById(item.getSalesmanId()), content);//电话和内容

        }


    }

    private void sendWxMsg(AuditState state, AuditLog auditLog, SoOrder soOrder) {
        if (state.equals(AuditState.PASS) && auditLog.getLevel().equals(auditLog.getMaxLevel())) {
            //推送icompany公众号的模板消息
            try {
                accountWeiXinService.saveOrderMsg(soOrder.getAccountMobile(), soOrder.getId());
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

}
