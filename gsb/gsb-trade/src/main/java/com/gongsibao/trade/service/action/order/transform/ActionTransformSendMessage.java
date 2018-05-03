package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.uc.User;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActionTransformSendMessage
 * @Description:TODO
 * @author: 郭佳
 * @date: 2018/4/28  11:59
 */
public class ActionTransformSendMessage implements IAction {

    private String orderNo = "";//订单编号
    private int orderLengh = 0;//几个订单
    private Boolean flagEnd = false;//是否最后
    HashMap<Integer, Integer> hashFrom = new HashMap<Integer, Integer>();//被转走的业务员订单数量
    private String title = "转移";//转移和分配


    @Override
    public void execute(ActionContext ctx) {
        //订单
        SoOrder entity = (SoOrder) ctx.getItem();
        orderNo = entity.getNo();
        //转移的目标业务员
        //获取额外参数
        Map<String, Object> statusMap = ctx.getStatus();
        int type = (int) statusMap.get("type");
        if (type == 1) {
            title = "分配";
        }

        orderLengh = (int) statusMap.get("orderLengh");
        flagEnd = (Boolean) statusMap.get("flagEnd");
        hashFrom = (HashMap<Integer, Integer>) statusMap.get("hashFrom");
        Salesman toUser = (Salesman) statusMap.get("toUser");
        //转移的目标业务员
        Salesman formUser = (Salesman) statusMap.get("formUser");

        if (orderLengh > 1 && flagEnd) {//批量并且走到最后

            batchToUserSendMsg(toUser);//接受业务员消息
            if (formUser!=null){
                batchFormUserSendMsg(hashFrom, toUser.getName());//被转移分配业务员消息
            }


        } else {//单个
            //发送消息
            toUserSendMsg(toUser);//接受业务员消息
            if (formUser!=null){
                formUserSendMsg(formUser, toUser.getName());//被转移分配业务员消息
            }

        }


    }

    /**
     * @param hashFrom
     * @param toName
     * @author: 郭佳
     * @Description:TODO被转移分配业务员消息
     * @date: 2018/4/28 15:46
     */
    private void batchFormUserSendMsg(HashMap<Integer, Integer> hashFrom, String toName) {
        if (hashFrom == null) {
            return;
        }
        for (Map.Entry<Integer, Integer> entry : hashFrom.entrySet()) {
            Integer fromUserId = entry.getKey();
            Integer num = entry.getValue();

            String content = String.format("【批量%s提醒】您好，【%s】把您的%s个订单分配给【%s】，请知悉", title, SessionManager.getUserName(), num, toName);
            SmsHelper.send(UserHelper.getEmployeTelById(fromUserId), content);//电话和内容
            batchFromUserLeaderSendMsg(fromUserId, num, toName);//给相关领导发提醒
        }


    }

    /**
     * @param fromUserId
     * @param num
     * @param toName
     * @author: 郭佳
     * @Description:TODO 给相关领导发提醒
     * @date: 2018/4/28 16:04
     */
    private void batchFromUserLeaderSendMsg(Integer fromUserId, Integer num, String toName) {

        List<String> tels = UserHelper.getSalesmanLeaders(fromUserId);
        for (String tel : tels
                ) {
            if (!StringManager.isNullOrEmpty(tel)) {
                String content = String.format("【批量%s提醒】您好，【%s】把【%s】的%s个订单分配给【%s】，请知悉", title, SessionManager.getUserName(), UserHelper.getEmployeeName(fromUserId), orderLengh, toName);
                SmsHelper.send(tel, content);//电话和内容
            }

        }

    }

    /**
     * @param toUser
     * @author: 郭佳
     * @Description:TODO 批量通知转向业务员
     * @date: 2018/4/28 15:18
     */
    private void batchToUserSendMsg(Salesman toUser) {
        if (toUser == null) {
            return;
        }
        String content = String.format("【批量%s提醒】您好，【%s】批量分配给您%s个订单，请及时跟进", title, SessionManager.getUserName(), orderLengh);

        SmsHelper.send(UserHelper.getEmployeTelById(toUser.getEmployeeId()), content);//电话和内容

        batchToUserLeaderSendMsg(toUser);//给相关领导发提醒

    }

    /**
     * @param toUser
     * @author: 郭佳
     * @Description:TODO 批量通知接受订单业务员领导
     * @date: 2018/4/28 15:20
     */
    private void batchToUserLeaderSendMsg(Salesman toUser) {
        List<String> tels = UserHelper.getSalesmanLeaders(toUser.getEmployeeId());
        for (String tel : tels
                ) {
            if (!StringManager.isNullOrEmpty(tel)) {
                String content = String.format("【批量%s提醒】您好，【%s】批量分配给【%s，取值】%S个订单，请知悉", title, SessionManager.getUserName(), toUser.getName(), orderLengh);
                SmsHelper.send(tel, content);//电话和内容
            }

        }

    }

    /**
     * @param formUser
     * @author: 郭佳
     * @Description:TODO 被转移分配业务员消息
     * @date: 2018/4/28 13:44
     */
    private void formUserSendMsg(Salesman formUser, String toName) {
        if (formUser == null) {
            return;
        }
        String content = String.format("【%s提醒】您好，【%s】把您的1个订单分配给【%s】，订单编号为【%s】，请知悉", title, SessionManager.getUserName(), toName, orderNo);

        SmsHelper.send(UserHelper.getEmployeTelById(formUser.getEmployeeId()), content);//电话和内容
        formUserLeaderSendMsg(formUser, toName);//给相关领导发提醒
    }

    /**
     * @param formUser
     * @author: 郭佳
     * @Description:TODO 给相关领导发提醒  转移走
     * @date: 2018/4/28 16:01
     */
    private void formUserLeaderSendMsg(Salesman formUser, String toName) {

        List<String> tels = UserHelper.getSalesmanLeaders(formUser.getEmployeeId());
        for (String tel : tels
                ) {
            if (!StringManager.isNullOrEmpty(tel)) {
                String content = String.format("【%s提醒】您好，【%S】把【%s】的1个订单分配给【%s】，订单编号为【%s】，请知悉", title, SessionManager.getUserName(), formUser.getName(), toName, orderNo);
                SmsHelper.send(tel, content);//电话和内容
            }

        }

    }

    /**
     * @param toUser
     * @author: 郭佳
     * @Description:TODO 接受业务员消息
     * @date: 2018/4/28 13:44
     */
    private void toUserSendMsg(Salesman toUser) {
        if (toUser == null) {
            return;
        }
        String content = String.format("【%s提醒】您好，【%s】分配给您1个订单，订单编号为【%s】，请及时跟进", title, SessionManager.getUserName(), orderNo);

        SmsHelper.send(UserHelper.getEmployeTelById(toUser.getEmployeeId()), content);//电话和内容//toUser.getMobile()

        toUserLeaderSendMsg(toUser);//给相关领导发提醒

    }

    /**
     * @param toUser
     * @author: 郭佳
     * @Description:TODO 给相关领导发提醒
     * @date: 2018/4/28 13:52
     */
    private void toUserLeaderSendMsg(Salesman toUser) {

        List<String> tels = UserHelper.getSalesmanLeaders(toUser.getEmployeeId());
        for (String tel : tels
                ) {
            if (!StringManager.isNullOrEmpty(tel)) {
                String content = String.format("【%s提醒】您好，【%s】分配给【%s】1个订单，订单编号为【%s】，请知悉", title, SessionManager.getUserName(), toUser.getName(), orderNo);
                SmsHelper.send(tel, content);//电话和内容
            }

        }

    }


}
