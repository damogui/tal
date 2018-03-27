package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

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


    /*获取订单id根据no*/
    @Override
    public Integer getOrderIdByNo(Integer orderNo) {
        IPersister<SoOrder> orderService = PersisterFactory.create ();
        String sql = "SELECT  IFNULL(MAX(pkid),0) FROM so_order  WHERE  no=? ;";//根据订单编号获取订单id
        QueryParameters qps = new QueryParameters ();
        qps.add ("@no", orderNo, Types.INTEGER);//订单编号
        Integer orderId = orderService.executeInt (sql, qps);
        return orderId;
    }

    /*根据参数值更细状态值*/
    @Override
    public void updateStatus(String status_id, Integer id, AuditStatusType shzt) {

        UpdateBuilder updateBuilder = new UpdateBuilder ();
        {
            updateBuilder.update ("so_order");
            updateBuilder.set (status_id, shzt.getValue ());
            if (shzt.equals (AuditStatusType.Shtg)&&status_id.equals ("dep_receivable_audit_status_id")) {
                updateBuilder.set ("performancePrice", "payable_price");//订单业绩
            }
            if (shzt.equals (AuditStatusType.Shtg)&&status_id.equals ("dep_payper_audit_status_id")) {
                updateBuilder.set ("returned_price","paid_price-refund_price-returned_price-carry_amount");//回款业绩待划分金额,计算公式
                //paid_price-refund_price-returned_price-carry_amount
            }


            updateBuilder.where ("pkid=?");
        }
        String sql = updateBuilder.toSQL ();
        QueryParameters qps = new QueryParameters ();
        qps.add ("@pkid", id, Types.INTEGER);
        this.pm.executeNonQuery (sql, qps);

    }




}