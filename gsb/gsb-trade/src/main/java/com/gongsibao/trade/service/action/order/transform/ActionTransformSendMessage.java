package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.Refund;
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
    @Override
    public void execute(ActionContext ctx) {


    }


}
