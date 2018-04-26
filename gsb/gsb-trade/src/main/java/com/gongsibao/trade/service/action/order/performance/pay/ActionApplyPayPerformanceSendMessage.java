package com.gongsibao.trade.service.action.order.performance.pay;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.service.action.order.utils.UserHelper;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ActionApplyPerformanceSendMessage
 * @Description:TODO 创建回款业绩通知
 * @author: 韩伟
 * @date: 2018年3月22日 下午6:44:16
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionApplyPayPerformanceSendMessage implements IAction {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);//订单服务

    @Override
    public void execute(ActionContext ctx) {

        List<NDepPay> depPayList = (List<NDepPay>) ctx.getItem();
        //发送消息待审核
        Map<String, Object> objectMap = ctx.getStatus();
        List<AuditLog> audits = (List<AuditLog>) objectMap.get("audits");
        if (audits.size() < 1) {
            return;
        }
        List<String> tels = new ArrayList<>();
        for (AuditLog item : audits
                ) {
            if (item.getLevel() == 1) {

                tels.add(UserHelper.getEmployeTelById(item.getCreatorId()));
            }

        }
        SoOrder order = orderService.getSoOrderById(depPayList.get(0).getOrderId(), "");
        auditSend(order, tels);




    }

    /*进行发送消息*/
    private void auditSend(SoOrder soOrder,List<String>tels) {

        if (soOrder == null ) {
            return;
        }
        for (String tel:
                tels) {
            if (!StringManager.isNullOrEmpty(tel)){
                String content = String.format("【回款业绩待审核提醒】您好，【%s】提交1个回款业绩申请待您审核，订单编号为【%S】，请及时审核", UserHelper.getEmployeeName(soOrder.getOwnerId()),soOrder.getNo());
                SmsHelper.send(tel, content);//电话和内容
            }

        }

    }

}
