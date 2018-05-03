package com.gongsibao.trade.service.action.audit.orderNewSave;

import java.util.List;
import java.util.Map;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.bd.service.auditLog.AuditContext;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;

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
        try {
            auditSend(state, auditLog, soOrder, remark);
            //推送icompnay公众号消息
            sendWxMsg(state, auditLog, soOrder);
            //钉钉播报
            sendDingTalk(state, auditLog, soOrder.getId());

        }catch (Exception e){
            //暂时不处理

        }


    }

    /*进行发送消息*/
    private void auditSend(AuditState state, AuditLog auditLog, SoOrder soOrder, String remark) {

        if (soOrder == null || auditLog == null) {

            return;
        }
        switch (state.getValue()) {
            case 0://驳回审核
                if (soOrder.getOwner() != null) {

                    String content = String.format("【改价审核提醒】您好，您有1个订单提交的改价申请审核不通过，订单编号为【%s】，原因为【%S】,请知悉", soOrder.getNo(),remark);
                    SmsHelper.send(soOrder.getOwner().getMobile(), content);//订单业务员
                }
                break;
            case 1://通过审核
                if (auditLog.getLevel().equals(auditLog.getMaxLevel())) {
                    //通过审核
                    String content = String.format("【改价审核提醒】您好，您有1个订单提交的改价申请已审核通过，订单编号为【%s】，请知悉", soOrder.getNo());
                    SmsHelper.send(UserHelper.getEmployeTelById(soOrder.getOwnerId()), content);//订单业务员

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
            String content = String.format("【改价待审核提醒】您好，有1个改价订单待您审核，请及时审核");
            SmsHelper.send(UserHelper.getEmployeTelById(item), content);//电话和内容
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

    //钉钉播报
    private void sendDingTalk(AuditState state, AuditLog auditLog, Integer orderId) {
        if (state.equals(AuditState.PASS) && auditLog.getLevel().equals(auditLog.getMaxLevel())) {
            ActionContext dingtackctx = new ActionContext();
            {
                dingtackctx.setPath("gsb/bd/dingtalk/broadcast");
                dingtackctx.setItem(orderId);
            }

            ActionManager action = new ActionManager();
            action.execute(dingtackctx);
        }

    }

}
