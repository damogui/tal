package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.entity.trade.Contract;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;

/**
 * Created by zhangchao on 2018/3/21.
 */
/*合同申请发送消息*/
public class ActionApplyContractSendMessage implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();
    }
}