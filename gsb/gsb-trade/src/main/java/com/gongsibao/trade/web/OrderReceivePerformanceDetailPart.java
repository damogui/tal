package com.gongsibao.trade.web;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.List;

/**
 * Created by win on 2018/3/12.
 */
/*创建回款业绩的js控制器*/
public class OrderReceivePerformanceDetailPart extends DetailPart {

    /*NDepReceivable 业绩实体类 prodTraceService*/
    INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);


    /*根据订单号获取订单的支付信息针对线上支付*/
    public int getOnlinePayInfoBySoderOId(Integer orderId) {
        ISoOrderService soOrderService = ServiceFactory.create (ISoOrderService.class);
        SoOrder soOrder = soOrderService.byId (orderId);

        List<OrderPayMap> pays = soOrder.getPays ();
        if (pays.size () > 0) {//注意是否判断已经划分回款业绩金额
            return pays.get (0).getPayId ();
        } else {

            return 0;

        }


    }

    /*校验订单是否存在且付款金额小于订单金额*/
    public int checkOrderId(Integer orderId) {
        IPersister<SoOrder> orderService = PersisterFactory.create ();
        String sql = "SELECT  COUNT(1)  FROM  so_order  WHERE   `no`=?  AND  paid_price<total_price;";
        QueryParameters qps = new QueryParameters ();
        qps.add ("@no", orderId, Types.INTEGER);
        int num = orderService.executeInt (sql, qps);//1存在符合条件的订单0不存在
        return num;
    }


}
