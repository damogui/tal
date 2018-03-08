package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;

import java.util.Map;

/**
 * Created by zhangchao on 2018/3/8.
 * 订单分配/转移：验证
 */
public class ActionTransformVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        //订单
        SoOrder entity = (SoOrder) ctx.getItem();

        // 验证非空
        if (entity == null) {
            throw new BusinessException("该订单不存在！");
        }

        //获取额外参数
        Map<String, Object> statusMap = ctx.getStatus();

        if (statusMap == null) {
            throw new BusinessException("额外参数不能为空！");
        }

        //转移的目标业务员
        Salesman toUser = (Salesman) statusMap.get("toUser");

        if (toUser == null) {
            throw new BusinessException("目标业务员不存在！");
        }
    }
}

