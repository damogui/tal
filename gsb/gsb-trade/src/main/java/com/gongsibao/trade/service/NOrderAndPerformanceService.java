package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.trade.base.INOrderAndPerformanceService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.entity.IPersistable;
import org.netsharp.service.PersistableService;

/**
 * 订单业绩和回款业绩
 */
@Service
public class NOrderAndPerformanceService extends PersistableService<SoOrder> implements INOrderAndPerformanceService {

    public NOrderAndPerformanceService() {
        super ();
        this.type = SoOrder.class;
    }

    /*回款业绩保存*/
    @Override
    public int saveNDepReceivableBySoder(Pay entity) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/pay/performance");//预置的回款业绩保存
            ctx.setItem (entity);
            ctx.setState (EntityState.New);
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        entity = (Pay) ctx.getItem ();
        return 1;
    }
    /*订单业绩保存*/
    @Override
    public IPersistable saveOrderPerformance(SoOrder entity) {


        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/performance");//订单业绩保存
            ctx.setItem (entity);
            ctx.setState (EntityState.Persist);//修改业绩
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        entity = (SoOrder) ctx.getItem ();
        return null;
    }
}
