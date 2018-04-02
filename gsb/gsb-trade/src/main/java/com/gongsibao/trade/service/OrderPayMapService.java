package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IOrderService;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
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
            oql.getParameters ().add ("orderId", orderId, Types.INTEGER);
        }
        return this.queryList (oql);
    }

    @Override
    public OrderPayMap queryByPayId(Integer payId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("pay_id=?");
            oql.getParameters ().add ("pay_id", payId, Types.INTEGER);
        }
        return this.queryFirst (oql);
    }

    /*最后的回写支付挂靠到订单*/
    @Override
    public int updateByPayId(Integer payId) {
        QueryParameters qpsPay = new QueryParameters ();
        qpsPay.add ("@pkid", payId, Types.INTEGER);
        String sqlPay = "UPDATE   so_pay SET  offline_audit_status_id=1054  WHERE  pkid=?";
        Integer num = this.pm.executeNonQuery (sqlPay, qpsPay);//进行更新回款

        return num;
    }


}