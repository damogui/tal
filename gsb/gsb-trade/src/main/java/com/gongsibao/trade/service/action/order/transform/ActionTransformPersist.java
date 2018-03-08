package com.gongsibao.trade.service.action.order.transform;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.SoOrder;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.Map;

/**
 * Created by zhangchao on 2018/3/8.
 * 订单分配/转移：保存
 */
public class ActionTransformPersist implements IAction {

    IPersister<SoOrder> orderPm = PersisterFactory.create();

    @Override
    public void execute(ActionContext ctx) {

        //订单
        SoOrder entity = (SoOrder) ctx.getItem();

        //获取额外参数
        Map<String, Object> statusMap = ctx.getStatus();

        //转移的目标业务员
        Salesman toUser = (Salesman) statusMap.get("toUser");

        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order");
            updateBuilder.set("owner_id", toUser.getEmployeeId());
            updateBuilder.where("pkid = ? ");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", entity.getId(), Types.INTEGER);
        orderPm.executeNonQuery(sql, qps);


    }
}
