package com.gongsibao.trade.service;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IOrderPayMapService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "  UPDATE  so_pay  SET  confirm_time=? WHERE  pkid=?  ";

        QueryParameters qps = new QueryParameters ();
        qps.add ("@confirm_time", payTime, Types.DATE);
        qps.add ("@pkid", payId, Types.INTEGER);
        Integer num = this.pm.executeNonQuery (sql, qps);
        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);
        Oql oql = new Oql ();
        oql.setType (OrderPayMap.class);
        oql.setFilter ("pay_id=?");
        oql.setSelects ("OrderPayMap.*,");
        oql.setSelects ("OrderPayMap.soOrder");
        List<OrderPayMap> orderPayMapList = new ArrayList<> ();
        orderPayMapList = orderPayMapService.queryList (oql);

        for (OrderPayMap  item:orderPayMapList
             ) {

            if (item.getSoOrder ().getFistPayTime ()==null){


            }

        }


        return num;

    }

}