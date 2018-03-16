package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.trade.base.INOrderAndPerformanceService;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.core.EntityState;
import org.netsharp.service.PersistableService;

/**
 * 订单业绩和回款业绩
 */
public class OrderAndPerformanceService extends PersistableService<SoOrder> implements INOrderAndPerformanceService {

    public OrderAndPerformanceService() {
        super ();
        this.type = SoOrder.class;
    }

    /*回款业绩保存*/
    @Override
    public int saveNDepReceivableBySoder(DepPayMapDTO entity) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/pay/performance");//预置的回款业绩保存
            ctx.setItem (entity);
            ctx.setState (EntityState.New);
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        entity = (DepPayMapDTO) ctx.getItem ();
        return 1;
    }
}
