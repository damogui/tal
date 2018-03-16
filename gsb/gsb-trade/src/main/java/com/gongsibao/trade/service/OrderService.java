package com.gongsibao.trade.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    public OrderService() {
        super ();
        this.type = SoOrder.class;
    }

    @Override
    public SoOrder save(SoOrder entity) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/save");
            ctx.setItem (entity);
            ctx.setState (entity.getEntityState ());
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);

        entity = (SoOrder) ctx.getItem ();
        return entity;
    }





    @Override
    public Boolean applyStage(SoOrder entity) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/stage");
            ctx.setItem (entity);
            ctx.setState (entity.getEntityState ());
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        return true;
    }

    @Override
    public Boolean applyRefund(Refund refund) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/refund");
            ctx.setItem (refund);
            ctx.setState (refund.getEntityState ());
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        return true;
    }

    @Override
    public Boolean applyCarryover(NOrderCarryover orderCarryover) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/carryover");
            ctx.setItem (orderCarryover);
            ctx.setState (orderCarryover.getEntityState ());
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        return true;
    }

    @Override
    public SoOrder getByOrderId(Integer orderId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("pkid =" + orderId);
        }
        SoOrder entity = super.queryFirst (oql);
        return entity;
    }

    @Override
    public SoOrder getByOrderNo(String orderNo) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("no =" + orderNo);
        }
        SoOrder entity = super.queryFirst (oql);
        return entity;
    }



}