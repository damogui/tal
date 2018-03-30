package com.gongsibao.trade.service.action.order.utils;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IOrderPayMapService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guojia on 2018/3/30.
 */
public class AuditHelper {
    /*查询是否存在已经审核的记录*/
    private static IPersister<AuditLog> auditLogService = PersisterFactory.create ();

    public static Integer getRecode(Integer formId, Integer typeId) {

        String sql = String.format ("SELECT  IFNULL(MAX(form_id),0) FROM  bd_audit_log  WHERE  type_id=%s  and  status_id=1051  AND     form_id=? ", typeId);//查询是否存在订单业绩审核状态
        QueryParameters qps = new QueryParameters ();
        qps.add ("@form_id", formId, Types.INTEGER);
        Integer execNum = auditLogService.executeInt (sql, qps);
        return execNum;

    }

    /*创建一笔回款的时候是否存在待审核*/
    public static Integer checkPayIsAudit(Pay pay) {
        List<Integer> listOrders = new ArrayList<> ();
        for (OrderPayMap item :
                pay.getOrderPayMaps ()) {
            listOrders.add (item.getOrderId ());
        }
        if (listOrders.size () > 0) {
            String orderIds = StringManager.join (",", listOrders);
            List<Integer> listPayIds = new ArrayList<> ();

            IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);

            Oql oql = new Oql ();
            oql.setType (OrderPayMap.class);
            oql.setSelects ("pay_id");
            oql.setFilter (String.format ("  where order_id in (%s)", orderIds));
            List<OrderPayMap> listOrderPayMap = orderPayMapService.queryList (oql);
            for (OrderPayMap item2 :
                    listOrderPayMap) {
                listPayIds.add (item2.getPayId ());
            }

            if (listPayIds.size () > 0) {


                IPersister<AuditLog> auditLogServer = PersisterFactory.create ();
                String payIds = StringManager.join (",", listPayIds);

                String sql = String.format ("SELECT    COUNT(1)  FROM   bd_audit_log   WHERE  type_id=1045  AND  status_id=1051    AND  form_id IN (%s) ", payIds);//回款审核
                Integer num = auditLogServer.executeInt (sql, null);

                return num;


            }


        } else {

            return 1;

        }

        return 0;
    }
}