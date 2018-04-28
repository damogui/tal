package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActionTransformSendMessage
 * @Description:TODO
 * @author: 郭佳
 * @date: 2018/4/28  11:59
 */
public class ActionTransformSendMessage implements IAction {

    private    String orderNo="";//订单编号
    @Override
    public void execute(ActionContext ctx) {
        //订单
        SoOrder entity = (SoOrder) ctx.getItem();
        orderNo=entity.getNo();
        //转移的目标业务员
        //获取额外参数
        Map<String, Object> statusMap = ctx.getStatus();
        Salesman toUser = (Salesman) statusMap.get("toUser");
        //转移的目标业务员
        Salesman formUser = (Salesman) statusMap.get("formUser");
        //发送消息待审核

        toUserSendMsg(toUser);//接受业务员消息
        formUserSendMsg(formUser);//被转移分配业务员消息


//
//        if (audits.size()<1){
//            return;
//        }
//        List<String> tels=new ArrayList<>();
//        for (AuditLog  item:audits
//                ) {
//            if (item.getLevel()==1){
//
//                tels.add(UserHelper.getEmployeTelById(item.getCreatorId()));
//            }
//
//        }
//        auditSend(entity,tels);


    }
    /**
     * @author: 郭佳
     * @param formUser
     * @Description:TODO 被转移分配业务员消息
     * @date:   2018/4/28 13:44
     */
    private void formUserSendMsg(Salesman formUser) {
        if (formUser == null) {
            return;
        }
        String content = String.format("【分配提醒】您好，【%s】把您的1个订单分配给【业务员】，订单编号为【%s】，请知悉", SessionManager.getUserName(), orderNo);

        SmsHelper.send(formUser.getMobile(), content);//电话和内容

    }

    /**
     * @author: 郭佳
     * @param toUser
     * @Description:TODO 接受业务员消息
     * @date:   2018/4/28 13:44
     */
    private void toUserSendMsg(Salesman toUser) {
        if (toUser == null) {
            return;
        }
        String content = String.format("【分配提醒】您好，【%s】分配给您1个订单，订单编号为【%s】，请及时跟进", SessionManager.getUserName(), orderNo);

        SmsHelper.send(toUser.getMobile(), content);//电话和内容

        toUserLeaderSendMsg(toUser);//给相关领导发提醒

    }
    /**
     * @author: 郭佳
     * @param toUser
     * @Description:TODO 给相关领导发提醒
     * @date:   2018/4/28 13:52
     */
    private void toUserLeaderSendMsg(Salesman toUser) {

        List<String> tels=UserHelper.getSalesmanLeaders(toUser.getId());
        for (String  tel:tels
             ) {
            String content = String.format("【分配提醒】您好，【%s】分配给【%s】1个订单，订单编号为【%s】，请知悉", SessionManager.getUserName(), toUser.getName(),orderNo);
            SmsHelper.send(tel, content);//电话和内容
        }

    }


}
