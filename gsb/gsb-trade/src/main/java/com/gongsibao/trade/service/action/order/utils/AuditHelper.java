package com.gongsibao.trade.service.action.order.utils;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderInvoiceMapService;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IOrderService;
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

    private static IPersister<AuditLog> auditLogService = PersisterFactory.create();
    static IOrderService orderService = ServiceFactory.create(IOrderService.class);

    /*查询是否存在已经审核的记录*/
    public static Integer getRecode(Integer formId, Integer typeId) {
        String sql = "";
        switch (typeId) {
            case 1050://订单业绩申请
                sql = String.format("SELECT  IFNULL(MAX(form_id),0) FROM  bd_audit_log  WHERE  type_id=%s  and  status_id in (1051,1054)  AND     form_id=? ", typeId);//查询是否存在订单业绩审核状态或者审核状态
                break;

            case 1051://回款业绩申请
                sql = String.format("SELECT  IFNULL(MAX(form_id),0) FROM  bd_audit_log  WHERE  type_id=%s  and  status_id in (1051,1052)  AND     form_id=? ", typeId);//查询是否存在订单业绩审核状态或者审核状态
                break;


        }


        QueryParameters qps = new QueryParameters();
        qps.add("@form_id", formId, Types.INTEGER);
        Integer execNum = auditLogService.executeInt(sql, qps);
        return execNum;

    }

    /*创建一笔回款的时候是否存在待审核*/
    public static Integer checkPayIsAudit(Pay pay) {
        List<Integer> listOrders = new ArrayList<>();
        for (OrderPayMap item :
                pay.getOrderPayMaps()) {
            listOrders.add(item.getOrderId());
        }
        if (listOrders.size() > 0) {
            String orderIds = StringManager.join(",", listOrders);
            List<Integer> listPayIds = new ArrayList<>();

            IOrderPayMapService orderPayMapService = ServiceFactory.create(IOrderPayMapService.class);

            Oql oql = new Oql();
            oql.setType(OrderPayMap.class);
            oql.setSelects("pkid,pay_id");//orderPayMap.*
            oql.setFilter(String.format("order_id in (%s)", orderIds));
            List<OrderPayMap> listOrderPayMap = orderPayMapService.queryList(oql);
            for (OrderPayMap item2 :
                    listOrderPayMap) {
                if (item2.getPayId() != null) {
                    listPayIds.add(item2.getPayId());
                }

            }

            if (listPayIds.size() > 0) {


                IPersister<AuditLog> auditLogServer = PersisterFactory.create();
                String payIds = StringManager.join(",", listPayIds);

                String sql = String.format("SELECT    COUNT(1)  FROM   bd_audit_log   WHERE  type_id=1045  AND  status_id=1051    AND  form_id IN (%s) ", payIds);//回款审核
                Integer num = auditLogServer.executeInt(sql, null);

                return num;


            }


        } else {

            return 1;

        }

        return 0;
    }

    /*判断订单是不是待转入结转*/
    public static Integer getCarryRecode(Integer id, AuditStatusType type) {

        IPersister<NOrderCarryover> carryService = PersisterFactory.create();
        String sql = "SELECT  count(1) FROM    bd_audit_log WHERE  type_id= 1052 AND status_id=?   AND  form_id IN (SELECT  id  FROM  so_order_carryover   WHERE  to_order_id=? ) ";//校验订单是不是存在待结转审核的
        QueryParameters qps = new QueryParameters();
        qps.add("@status_id", type.getValue(), Types.INTEGER);
        qps.add("@to_order_id", id, Types.INTEGER);
        Integer num = carryService.executeInt(sql, qps);

        return num;
    }

    /*根据外键 formId类型typeId level 等级进行查询下一级的userIds*/
    public static List<Integer> getNextLevelUserIds(Integer fromId, int typeId, int level) {

        IAuditLogService auditService = ServiceFactory.create(IAuditLogService.class);//订单业绩服务

        List<AuditLog> audits = auditService.getNextLevelUserIds(fromId, typeId, level);
        List<Integer> list = new ArrayList<>();

        for (AuditLog item : audits
                ) {
            list.add(item.getCreatorId());

        }
        return list;

    }

    /*根据订单id获取订单编号*/
    public static String getOrderNoById(Integer orderId) {
        IOrderService orderService = ServiceFactory.create(IOrderService.class);
        String no = orderService.getOrderNoById(orderId);
        return no;

    }

    /*根据发票id获取订单编号*/
    public static String getOrderNosByInvoiceId(Integer id) {
//        String  sql="SELECT  order_id FROM so_order_invoice_map  WHERE invoice_id=?";

        IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);
        List<OrderInvoiceMap> listMaps = orderInvoiceMapService.getByInvoiceId(id);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (OrderInvoiceMap item : listMaps
                ) {
            i++;
            sb.append(item.getSoOrder().getNo());
            if (listMaps.size() != i) {
                sb.append(",");
            }

        }
        return sb.toString();

    }

    /*根据订单id获取订单信息*/
    public static SoOrder getOrderById(int toOrderId) {

        return orderService.getSoOrderById(toOrderId, "");

    }

    /*根据发票id获取相关订单的业务员电话*/
    public static List<String> getOwnerTelsByInvoiceId(Integer invoiceId) {
        IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);
        List<OrderInvoiceMap> listMaps = orderInvoiceMapService.getByInvoiceId(invoiceId);
        List<String> tels = new ArrayList<>();
        for (OrderInvoiceMap item : listMaps
                ) {
            tels.add(UserHelper.getEmployeTelById(item.getSoOrder().getOwnerId()));
        }
        return tels;


    }
}
