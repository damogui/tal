package com.gongsibao.trade.service.action.order.contract;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

/**
 * Created by zhangchao on 2018/3/21.
 */
public class ActionApplyContractUpdateOrder implements IAction {

    IPersister<SoOrder> orderPm = PersisterFactory.create();
    @Override
    public void execute(ActionContext ctx) {
        //合同
        Contract contract = (Contract) ctx.getItem();

        UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
        {
            updateBuilder.update("so_order");
            updateBuilder.set("type", OrderType.Ht.getValue());
            updateBuilder.where("pkid = ? ");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", contract.getOrderId(), Types.INTEGER);
        orderPm.executeNonQuery(sql, qps);
    }
}
