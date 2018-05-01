package com.gongsibao.trade.service.action.order.save;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActionSaveOrderSendMessage
 * @Description:TODO 发送消息
 * 执行顺序：10
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:12:01
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderSendMessage implements IAction {

    private IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    @Override
    public void execute(ActionContext ctx) {
    	
        SoOrder soOrder = (SoOrder) ctx.getItem();
        //发送消息待审核
        Map<String, Object> objectMap = ctx.getStatus();
        List<AuditLog> audits = (List<AuditLog>) objectMap.get("audits");

        //改价或分期发送通知
        if (soOrder.getIsChangePrice() || soOrder.getIsInstallment()) {

            try {
            	
                sendChangePrice(audits, soOrder);
                
            } catch (Exception e) {
            	
            //暂时不处理

            }

        } else {
        	
        	//生成订单成功后，如果没有改价，则直接推送消息
            //推送icompany公众号的模板消息
            try {
            	
                accountWeiXinService.saveOrderMsg(soOrder.getAccountMobile(), soOrder.getId());
            } catch (Exception e) {
                //e.printStackTrace();
            }
            //钉钉播报
            try {
            	
                sendDingTalk(soOrder.getId());
            } catch (Exception e) {

            }
        }
    }

    //发送消息待审核（改价审核）
    private void sendChangePrice(List<AuditLog> audits, SoOrder soOrder) {
    	
        //发送消息待审核
        if (audits.size() < 1) {
        	
            return;
        }
        
        List<String> tels = new ArrayList<>();
        for (AuditLog item : audits) {
        	
            if (item.getLevel() == 1) {
            	
                tels.add(UserHelper.getEmployeTelById(item.getCreatorId()));
            }
        }
        auditSend(soOrder, tels);
    }

    /*进行发送消息*/
    private void auditSend(SoOrder soOrder, List<String> tels) {
    	
        if (soOrder == null) {
        	
            return;
        }
        
        for (String tel : tels) {
        	
            if (!StringManager.isNullOrEmpty(tel)) {
            	
                String content = String.format("【订单待审核提醒】您好，【%s】提交1个订单待您审核，订单编号为【%s】，请及时审核", UserHelper.getEmployeeName(soOrder.getOwnerId()), soOrder.getNo());
                SmsHelper.send(tel, content);//电话和内容
            }
        }
    }

    //钉钉播报
    private void sendDingTalk(Integer orderId) {
    	
        ActionContext dingtackctx = new ActionContext();
        {
            dingtackctx.setPath("gsb/bd/dingtalk/broadcast");
            dingtackctx.setItem(orderId);
        }

        ActionManager action = new ActionManager();
        action.execute(dingtackctx);
    }
}
