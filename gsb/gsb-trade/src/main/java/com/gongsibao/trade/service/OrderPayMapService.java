package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.trade.base.IOrderPayMapService;

@Service
public class OrderPayMapService extends PersistableService<OrderPayMap> implements IOrderPayMapService {

    public OrderPayMapService() {
        super ();
        this.type = OrderPayMap.class;
    }

    @Override
    public List<OrderPayMap> queryByOrderId(Integer orderId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("OrderPayMap.*,Pay.*");
            oql.setFilter ("orderId=?");
            oql.setOrderby(" add_time  desc ");
            oql.getParameters ().add ("orderId", orderId, Types.INTEGER);
        }
        return this.queryList (oql);
    }

    @Override
    public List<OrderPayMap> queryByPayId(Integer payId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("pay_id=?");
            oql.getParameters ().add ("pay_id", payId, Types.INTEGER);
        }
        return this.queryList (oql);
    }

//    /*最后的回写支付挂靠到订单*/
//    @Override
//    public int updateByPayIdss(Integer payId) {
//        QueryParameters qpsPay = new QueryParameters ();
//        qpsPay.add ("@pkid", payId, Types.INTEGER);
//        String sqlPay = "UPDATE   so_pay SET  offline_audit_status_id=1054  WHERE  pkid=?";
//        Integer num = this.pm.executeNonQuery (sqlPay, qpsPay);//进行更新回款
//
//        return num;
//    }


}