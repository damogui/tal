package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

    public PayService() {
        super ();
        this.type = Pay.class;
    }

    // @Override
    // public List<Pay> queryList(Oql oql) {
    // oql.setSelects
    // ("orderIds,id,payForOrderCount,payWayType,amount,u8BankId,offlineAuditStatus,createTime,creator,orderPayMaps.*,u8Bank.*");
    // // oql.setSelects ("u8Bank.*,");
    // // oql.setSelects ("orderPayMaps.*");
    // //List<Pay> list= super.queryList (oql);
    //
    //
    // List<Pay> list = super.queryList (oql);
    //
    // return list;
    // }

    @Override
    public Boolean applyPay(Pay pay) {

        ActionContext ctx = new ActionContext ();
        {
            ctx.setPath ("gsb/crm/order/pay");
            ctx.setItem (pay);
            ctx.setState (pay.getEntityState ());
        }
        ActionManager action = new ActionManager ();
        action.execute (ctx);
        return true;
    }

    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {

        UpdateBuilder updateBuilder = new UpdateBuilder ();
        {
            updateBuilder.update ("so_pay");
            updateBuilder.set ("offline_audit_status_id", auditStatusType.getValue ());
            updateBuilder.where ("pkid=?");
        }
        String sql = updateBuilder.toSQL ();
        QueryParameters qps = new QueryParameters ();
        qps.add ("id", id, Types.INTEGER);
        // throw  new BusinessException ("测试");
        this.pm.executeNonQuery (sql, qps);
    }


    /*审核通过之后进行处理*/
    @Override
    public Integer auditPass(String payTime, Integer payId) {
        String sql = "  UPDATE  so_pay  SET  offline_audit_status_id=1054,confirm_time=?,pay_audit_pass_time=now() WHERE" +
                "  pkid=?  ";

        QueryParameters qps = new QueryParameters ();
        qps.add ("@confirm_time", payTime, Types.DATE);
        qps.add ("@pkid", payId, Types.INTEGER);
        Integer num = this.pm.executeNonQuery (sql, qps);
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);
        Oql oql = new Oql ();
        oql.setType (OrderPayMap.class);
        oql.setFilter ("pay_id=?");
        oql.setSelects ("OrderPayMap.*,OrderPayMap.soOrder.*");
        oql.getParameters ().add ("@pay_id", payId, Types.INTEGER);
        List<OrderPayMap> orderPayMapList = new ArrayList<> ();
        orderPayMapList = orderPayMapService.queryList (oql);
        List<Integer> orderIds = new ArrayList<> ();//回款累加
        List<Integer> orderIdFirstAmount = new ArrayList<> ();//首次付款时间
        List<Integer> orderIdAllAmount = new ArrayList<> ();//全付款时间


        for (OrderPayMap item : orderPayMapList
                ) {
            SoOrder order = item.getSoOrder ();
            Integer payAmount = order.getPaidPrice ();
            Integer afterAmount = payAmount + item.getOrderPrice ();//审核通过之后加上原来的支付金额

            orderIds.add (item.getOrderId ());//所有的订单id，回款累加
            if (order.getFistPayTime () == null) {//首次付款（都需要判断是不是回款完成）
                orderIdFirstAmount.add (item.getOrderId ());

            }

            if (afterAmount.equals (order.getPayablePrice ())) {//支付完成
                orderIdAllAmount.add (item.getOrderId ());

            }

        }
        Integer execNum1 = 0;
        Integer execNum2 = 0;
        Integer execNum3 = 0;

        execNum1 = updateSorderPayInfo (orderPayMapList);
        if (orderIdFirstAmount.size () > 0) {
            execNum2 = updateFistPayInfo (orderIdFirstAmount, payTime);

        }
        if (orderIdAllAmount.size () > 0) {
            execNum3 = updateAllPayInfo (orderIdFirstAmount, payTime);

        }

        num += execNum1 + execNum2 + execNum3;
        return num;

    }

    /*支付完成所有的订单金额*/
    private Integer updateAllPayInfo(List<Integer> orderIdFirstAmount, String payTime) {
        String whereStr = "";
        if (orderIdFirstAmount.size () > 0) {
            whereStr = StringManager.join (",", orderIdFirstAmount);
        }
        String sql = String.format ("UPDATE   `so_order`  SET  pay_status_id=3013,pay_time=?   WHERE pkid IN (%s)", whereStr);

        QueryParameters qps = new QueryParameters ();
        qps.add ("@pay_time", payTime, Types.DATE);
        Integer num = this.pm.executeNonQuery (sql, qps);//进行更新  //如果回款和订单金额相等的话还要修改支付时间

        return num;

    }

    /*首次支付*/
    private Integer updateFistPayInfo(List<Integer> orderIdFirstAmount, String payTime) {
        String whereStr = "";
        if (orderIdFirstAmount.size () > 0) {
            whereStr = StringManager.join (",", orderIdFirstAmount);
        }
        String sql = String.format ("UPDATE   `so_order`  SET  pay_status_id=3012,fist_pay_time=now()   WHERE pkid IN (%s)", whereStr);

        QueryParameters qps = new QueryParameters ();
//        qps.add ("@fist_pay_time", payTime, Types.DATE);
        Integer num = this.pm.executeNonQuery (sql, qps);//进行更新  //如果回款和订单金额相等的话还要修改支付时间

        return num;
    }

    /*更新订单的支付状态*/
    private Integer updateSorderPayInfo(List<OrderPayMap> orderPayMapList) {
        Integer num = 0;
        for (OrderPayMap item : orderPayMapList
                ) {

            String sql = String.format ("UPDATE so_order SET  paid_price=paid_price+%s  WHERE  pkid=?", item.getOrderPrice ());//应该以分进行相加

            QueryParameters qps = new QueryParameters ();
            qps.add ("@pkid", item.getOrderId (), Types.INTEGER);

            num += this.pm.executeNonQuery (sql, qps);//进行更新回款


        }
        return num;


    }

}