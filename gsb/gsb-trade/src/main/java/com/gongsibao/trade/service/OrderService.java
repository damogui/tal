package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
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
            oql.setFilter ("pkid =?");
            oql.getParameters().add("@pkid",orderId,Types.INTEGER);
        }
        SoOrder entity = super.queryFirst (oql);
        return entity;
    }

    @Override
    public SoOrder getByOrderNo(String orderNo) {
        if (StringManager.isNullOrEmpty (orderNo)) {
            return null;
        }

        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("no =?");
            oql.getParameters().add("@no",orderNo,Types.VARCHAR);
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
        String sql = "";
        UpdateBuilder updateBuilder = new UpdateBuilder ();
        {

            if (shzt.equals (AuditStatusType.Shtg) && status_id.equals ("dep_receivable_audit_status_id")) {
                sql = String.format ("update  so_order  set performance_price=payable_price,%s=%s  where  pkid=? ", status_id, shzt.getValue ());
            }
            if (shzt.equals (AuditStatusType.Shtg) && status_id.equals ("dep_payper_audit_status_id")) {
                //updateBuilder.set ("returned_price","paid_price-refund_price-returned_price-carry_amount");//回款业绩待划分金额,计算公式
                sql = String.format ("update  so_order  set returned_price=paid_price-refund_price-carry_amount,%s=%s  where  pkid=? ", status_id, shzt.getValue ());
            }
        }
        if (sql.length () > 0) {
            QueryParameters qps = new QueryParameters ();
            qps.add ("@pkid", id, Types.INTEGER);
            this.pm.executeNonQuery (sql, qps);

        }


    }

    /*是否可以创建回款*/
    @Override
    public Integer checkCanPay(Integer orderId) {
        //校验余额是否小于应付金额 0是1不是
        int num = 0;
        num = checkIsBancleLessOrder (orderId);
        if (num > 0) {
            return num;//回款已经创建完毕
        }


        String sql = "SELECT COUNT(change_price_audit_status_id)  FROM so_order  WHERE  change_price_audit_status_id<>1054  AND  is_change_price=1 AND  pkid=?";//有没有待审核、审核中

        QueryParameters qps = new QueryParameters ();
        qps.add ("@pkid", orderId, Types.INTEGER);
        num = this.pm.executeInt (sql, qps);
        if (num > 0) {

            return 1;

        } else {
//            SoOrder order = byId (orderId);
            num = checkCanPayByOrderId (orderId);//订单是否存在已经支付的待审核
            return num;

        }
    }

    /*校验余额是否小于应付金额 0是1不是  等于也不行*/
    private int checkIsBancleLessOrder(Integer orderId) {

        SoOrder order = getSoOrderById (orderId,null);
        if (order.getBalance () < order.getPayablePrice ()) {
            return 0;

        } else {
            return 1;

        }


    }

    /*订单是否存在已经支付的待审核*/
    private int checkCanPayByOrderId(Integer orderId) {
        SoOrder sorder = getSoOrderById (orderId,"SoOrder.*,SoOrder.pays.*");
        List<Integer> listPayId = new ArrayList<> ();
        for (OrderPayMap item : sorder.getPays ()
                ) {
            listPayId.add (item.getPayId ());
        }


        if (listPayId.size () > 0) {
            String payIds = StringManager.join (",", listPayId);
            String sql = String.format ("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id   IN (1051,1052) and form_id in (%s)", AuditLogType.Sksq.getValue (), payIds);//待审核和审核中的

            int num = this.pm.executeInt (sql, null);
            if (num > 0) {
                return 1;

            } else {
                return 0;

            }
        }

        return 0;


    }

    /*是否可以创建   type=0（订单业绩），=1(回款业绩)*/
    @Override
    public Integer checkCanOrderPer(Integer orderId, Integer type) {
        String sql = String.format ("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id  NOT IN (0,1053) and form_id=?", AuditLogType.DdYjSq.getValue ());//无审核状态和驳回审核的不在查询列
        if (type == 1) {

            sql = String.format ("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id   IN (1051,1052) and form_id=?", AuditLogType.Skyjsh.getValue ());//待审核和审核中的
        }

        QueryParameters qps = new QueryParameters ();
        qps.add ("@form_id", orderId, Types.INTEGER);
        int num = this.pm.executeInt (sql, qps);
        if (num > 0) {
            return 1;

        } else {
            return 0;

        }
    }

    @Override
    public String getCustomerMobile(Integer orderId) {

        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("id,accountMobile");
            oql.setFilter ("pkid =" + orderId);
        }
        SoOrder entity = super.queryFirst (oql);

        return entity.getAccountMobile ();
    }

    @Override
    public NCustomer getCustomerByOrderId(Integer orderId) {

        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("id,customerId,customer.{id,realName,mobile,telephone,email,qq,weixin,addr}");
            oql.setFilter ("id =?");
            oql.getParameters ().add ("@orderId", orderId, Types.INTEGER);
        }
        SoOrder entity = super.queryFirst (oql);
        if (entity != null) {

            return entity.getCustomer ();
        }
        return null;
    }

    /*只是查询soorder的基础字段，不多查，效率高*/
    @Override
    public SoOrder getSoOrderById(Object id, String selects) {

        if (id == null) {

            id = "0";
        }
        Oql oql = new Oql ();

        StringBuilder sb = new StringBuilder ();
        sb.append ("SoOrder.*");
        if (!StringManager.isNullOrEmpty (selects)) {
            oql.setSelects (selects);
        } else {
            oql.setSelects (sb.toString ());
        }

        oql.setType (SoOrder.class);
        oql.setFilter ("pkid=?");
        oql.getParameters ().add ("@pkid", id, Types.INTEGER);
        SoOrder obj = queryFirst (oql);
        return obj;

    }


}